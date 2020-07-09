

log rotate
reporting engine (BIRT)
https/ssl
setup mail server
create security API
enable security API
add comments section
add role engine 
make css an import page
clean up UI so all the colors match



db.counter.aggregate(
  {
    $group: {
      _id: {
        name: '$query'
      },
      count: {
        $sum: 1
      }
    }
  }
)


