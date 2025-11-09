#!/bin/bash
# Weekly backup script for pasedb.org

set -e
BACKUP_DIR="/opt/apps/pasedb/backups"
LOG_FILE="/opt/apps/pasedb/backups/backup.log"
DATE=$(date +%F)
BACKUP_FILE="${BACKUP_DIR}/pasedb_${DATE}.sql.gz"

mkdir -p "$BACKUP_DIR"

# Dump the database to a compressed file
docker exec pasedb mysqldump -uroot -pteamrocket pasedb 2>>"$LOG_FILE" | gzip > "$BACKUP_FILE"
STATUS=$?

if [ $STATUS -eq 0 ]; then
    echo "$(date '+%F %T') - Backup successful: $BACKUP_FILE" >> "$LOG_FILE"
    # Delete all other backups, keep only the latest successful one
    find "$BACKUP_DIR" -type f -name "pasedb_*.sql.gz" ! -newer "$BACKUP_FILE" -delete
else
    echo "$(date '+%F %T') - Backup FAILED (exit code $STATUS)" >> "$LOG_FILE"
    # Keep previous backup until next success
    rm -f "$BACKUP_FILE"
fi

