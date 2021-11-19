from Scweet.scweet import scrape
from Scweet.user import get_user_information, get_users_following, get_users_followers
import sys

if len(sys.argv) == 3:
	data = scrape(words=[sys.argv[1]], since=sys.argv[2], until="2021-09-09", from_account = None, interval=1, 
              headless=True, display_type="Latest", save_images=False, 
              resume=True, filter_replies=False, proximity=False)
else : 
	print("argv is not matched")
