from Scweet.scweet import scrape
from Scweet.user import get_user_information, get_users_following, get_users_followers
import sys


if len(sys.argv) == 4:
	data = scrape( words=[sys.argv[1]] ,since=sys.argv[2], until=sys.argv[3], from_account = None, interval=1, 
              headless=False, display_type="Latest", save_images=False, 
              resume=False, filter_replies=False, proximity=False)
              
elif len(sys.argv) == 3:
	data = scrape(since=sys.argv[1], until=sys.argv[2], from_account = None, interval=1, 
              headless=False, display_type="Latest", save_images=False, 
              resume=False, filter_replies=False, proximity=False)
              
elif len(sys.argv) == 2:
	data = scrape(since=sys.argv[1], until=None, from_account = None, interval=1, 
              headless=False, display_type="Latest", save_images=False, 
              resume=False, filter_replies=False, proximity=False)
