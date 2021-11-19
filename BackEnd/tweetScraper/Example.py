from Scweet.scweet import scrape
from Scweet.user import get_user_information, get_users_following, get_users_followers


# scrape top tweets with the words 'covid','covid19' in proximity and without replies.
# the process is slower as the interval is smaller (choose an interval that can divide the period of time betwee, start and max date)

data = scrape(words=['bitcoin','ethereum'], since="2015-04-01", until="2015-04-02", from_account = None, interval=1, 
	headless=False, display_type="Top", save_images=False, 
	resume=False, filter_replies=False, proximity=False)
"""
# scrape top tweets of with the hashtag #covid19, in proximity and without replies.
# the process is slower as the interval is smaller (choose an interval that can divide the period of time betwee, start and max date)
https://www.youtube.com/watch?v=ootQs7sVulY
data = scrap(hashtag="bitcoin", since="2021-08-05", until=None, from_account = None, interval=1, 
	headless=True, display_type="Top", save_images=False, 
	resume=False, filter_replies=True, proximity=True)


# Get the main information of a given list of users
# These users belongs to my following. 

users = ['nagouzil', '@yassineaitjeddi', 'TahaAlamIdrissi', 
         '@Nabila_Gl', 'geceeekusuu', '@pabu232', '@av_ahmet', '@x_born_to_die_x']

# this function return a list that contains : 
# ["nb of following","nb of followers", "join date", "birthdate", "location", "website", "description"]

users_info = get_user_information(users, headless=True)


# Get followers and following of a given list of users
# Enter your username and password in .env file. I recommande you dont use your main account.
# Increase wait argument to avoid banning your account and maximise the crawling process if the internet is slow. I used 1 and it's safe.

# set your .env file with SCWEET_USERNAME and SCWEET_PASSWORD variables and provide its path
env_path = ".env"

following = get_users_following(users=users, env=env_path, verbose=0, headless=True, wait=2, limit=50, file_path=None)

followers = get_users_followers(users=users, env=env_path, verbose=0, headless=True, wait=2, limit=50, file_path=None)"""