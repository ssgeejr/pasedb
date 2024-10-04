class LinkItem:
    def __init__(self):
        self.url = ""
        self.title = ""
        self.description = ""
        self.img_url = ""
        self.display_height = 0
        self.display_width = 0
        self.comment = ""
        self.user_id = -101
        self.tags = []


    def toString(self):
        print(f"url: {self.url}")
        print(f"title: {self.title}")
        print(f"description: {self.description}")
        print(f"img_url: {self.img_url}")
        print(f"display_height: {self.display_height}")
        print(f"display_width: {self.display_width}")
        print(f"user_id: {self.user_id}")
        print(f"tags: {self.tags}")

    def set_url(self, url):
        self.url = url

    def set_title(self, title):
        self.title = title

    def set_description(self, description):
        self.description = description

    def set_imgurl(self, img_url):
        self.img_url = img_url

    def set_display_height(self, height):
        self.display_height = height

    def set_display_width(self, width):
        self.display_width = width

    def set_comment(self, comment):
        self.comment = comment

    def set_user_id(self, user_id):
        self.user_id = user_id

    def set_tags(self, tags):
        self.tags = tags
