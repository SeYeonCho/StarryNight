from Scweet.scweet import scrape
from Scweet.user import get_user_information, get_users_following, get_users_followers


data = scrape(words=['개'], since="2021-08-05", until=None, from_account = None, interval=2, 
              headless=True, display_type="Latest", save_images=False, 
              resume=False, filter_replies=True, proximity=False)

