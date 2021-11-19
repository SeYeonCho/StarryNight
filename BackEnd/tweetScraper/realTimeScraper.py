import json
import re
import random
import sys
import time
from collections import OrderedDict
from datetime import datetime
from time import sleep

from selenium import webdriver
import chromedriver_autoinstaller
from selenium.webdriver.firefox.options import Options
import requests

black_list_word= ['애인대행','섹','출장추천','채팅만남','수치플','변남','키스방','만남앱','즉석만남','출장전용','비아그라','부부만남',
'즉석추천','유흥','여탑','자위','번역망가','비떱','사컨','멜섭','대물','남성전신','핸드폰만남','성감마사지','오프후기','보빨','만남알바','미시','콜걸','대딸','핸플','펠라','사까시','출장','야노','변남','게이','입싸','성인만남','후장'
,'암캐','용돈만남','스폰녀','스팽','능욕','풋잡','초대남','성인용품','포르노','sex','러브샵','유두','오피','강간','변녀','섻','노출','글래머','건오'
,'레즈','도그플']
def init_driver(headless=False):
    # chromedriver_path = chromedriver_autoinstaller.install()
    # options = webdriver.ChromeOptions()
    options = Options()
    if headless is True:
        print("headless option")
        options.add_argument('--disable-gpu')
        options.headless = True
    else:
        options.headless = False
#        options.add_argument('window-size = 1920,1080')
#    options.add_argument('log-level=3')
    prefs = {"profile.managed_default_content_settings.images": 2}
    # options.add_experimental_option("prefs", prefs)
#    driver = webdriver.Chrome(options=options, executable_path=chromedriver_path)

    driver = webdriver.Firefox(firefox_options=options, executable_path = "/home/qwe/tacamedy/Scweet-master2/geckodriver")
    driver.set_page_load_timeout(100)
    return driver


def get_data(card, save_images=False, save_dir=None):
    """Extract data from tweet card"""
    image_links = []

    try:
        username = card.find_element_by_xpath('.//span').text
    except:
        return

    try:
        handle = card.find_element_by_xpath('.//span[contains(text(), "@")]').text
    except:
        return

    try:
        postdate = card.find_element_by_xpath('.//time').get_attribute('datetime')
        # print(postdate)
    except:
        return

    try:
        text = card.find_element_by_xpath('.//div[2]/div[2]/div[1]').text
    except:
        text = ""

    try:
        embedded = card.find_element_by_xpath('.//div[2]/div[2]/div[2]').text
    except:
        embedded = ""

    # text = comment + embedded

    try:
        reply_cnt = card.find_element_by_xpath('.//div[@data-testid="reply"]').text
    except:
        reply_cnt = 0

    try:
        retweet_cnt = card.find_element_by_xpath('.//div[@data-testid="retweet"]').text
    except:
        retweet_cnt = 0

    try:
        like_cnt = card.find_element_by_xpath('.//div[@data-testid="like"]').text
    except:
        like_cnt = 0

    try:
        elements = card.find_elements_by_xpath('.//div[2]/div[2]//img[contains(@src, "https://pbs.twimg.com/")]')
        for element in elements:
            image_links.append(element.get_attribute('src'))
    except:
        image_links = []

    # if save_images == True:
    #	for image_url in image_links:
    #		save_image(image_url, image_url, save_dir)
    # handle promoted tweets

    try:
        promoted = card.find_element_by_xpath('.//div[2]/div[2]/[last()]//span').text == "Promoted"
    except:
        promoted = False
    if promoted:
        return

    # get a string of all emojis contained in the tweet
    try:
        emoji_tags = card.find_elements_by_xpath('.//img[contains(@src, "emoji")]')
    except:
        return
    emoji_list = []
    for tag in emoji_tags:
        try:
            filename = tag.get_attribute('src')
            emoji = chr(int(re.search(r'svg\/([a-z0-9]+)\.svg', filename).group(1), base=16))
        except AttributeError:
            continue
        if emoji:
            emoji_list.append(emoji)
    emojis = ' '.join(emoji_list)

    # tweet url
    try:
        element = card.find_element_by_xpath('.//a[contains(@href, "/status/")]')
        tweet_url = element.get_attribute('href')
    except:
        return

    tweet = (
        username, handle, postdate, text, embedded, emojis, reply_cnt, retweet_cnt, like_cnt, image_links, tweet_url)
    return tweet


def keep_scrolling(driver, tweet_ids, scrolling, data, scroll, last_position, set_crash):
    while scrolling:
        sleep(random.uniform(0.5, 1.5))
        # get the card of tweets
        page_cards = driver.find_elements_by_xpath('//article[@data-testid="tweet"]')  # changed div by article
        for card in page_cards:
            tweet = get_data(card)
            if tweet is not None and tweet[0] is not None and tweet[1] is not None and tweet[2] is not None and tweet[4] is not None:
                flag = False
                print(tweet[4])
                for word in black_list_word:
                    if tweet[4].find(word) != -1:
                        flag = True
                        print(word)
                        break
                if flag is True:
                    print('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!')
                    print('fail')
                    print('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!')
                    print('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!')
                    print('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!')
                    print('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!')
                    print('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!')
                    continue
                # check if the tweet is unique
                # print("------------")
                # print('0',tweet[0])
                # print('1',tweet[1])
                # print('2',tweet[2])
                # print('3',tweet[3])
                # print('4',tweet[4])
                # print('5',tweet[5])
                # print('6',tweet[6])
                # print('7',tweet[7])
                # print('8',tweet[8])
                # print('9',tweet[9])
                # print("-------------")
                tweet_id = tweet[0] + tweet[1] + tweet[2] + tweet[4]
                # print('------------')
                # print(tweet_id)
                # print('------------')
                if tweet_id not in tweet_ids:
                    temp = OrderedDict()
                    temp["text"] = tweet[4]
                    headers = {'Content-Type': 'application/json; charset=utf-8'}
                    while True :
                        try:
                            res = requests.post("http://3.35.214.129:6061/collect/twit",data = json.dumps(temp),headers=headers)
                            if res.status_code == 200 :
                                print(res.status_code)
                                break
                        except:
                            print('sleep 15')
                            print('maybe server is down')
                            sleep(15)

#                    print(json.dumps(temp, ensure_ascii=False, indent="\t"))
                    print("send")
                    tweet_ids.add(tweet_id)
                    data.append(tweet)
                    last_date = str(tweet[2])
                    print("Tweet made at: " + str(last_date) + " is found.")
                else:
                    set_crash = True;
                    print("set crash")
                    return driver, tweet_ids, scrolling, data, scroll, set_crash
        scroll_attempt = 0
        # check scroll position
        while True:
            if scroll == 3:
                return driver, tweet_ids, scrolling, data, scroll, set_crash
            scroll += 1
            print("scroll ", scroll)
            sleep(random.uniform(0.5, 1.5))
            driver.execute_script('window.scrollTo(0, document.body.scrollHeight);')
            curr_position = driver.execute_script("return window.pageYOffset;")
            if last_position == curr_position:
                scroll_attempt += 1
                # end of scroll region
                if scroll_attempt >= 2:
                    scrolling = False
                    break
                else:
                    sleep(random.uniform(0.5, 1.5))  # attempt another scroll
            else:
                last_position = curr_position
                break
    return driver, tweet_ids, scrolling, data, scroll

    # driver = init_driver(True)


driver = init_driver(True)
driver.get('https://twitter.com/search?q=lang%3Ako%20-filter%3Areplies&src=typed_query&f=live')
tweet_ids = set()
scrolling = True
last_position = driver.execute_script("return window.pageYOffset;")
data = []
scroll = 0
set_crash = False

if len(sys.argv) == 2:
    deadline = datetime.strftime(datetime.strptime(sys.argv[1], '%Y-%m-%d'), '%Y-%m-%d')
    print(deadline)
    while True:
        now = datetime.strftime(datetime.now(), '%Y-%m-%d')
        print(now)
        if now > deadline:
            break
        print('start')
        driver, tweet_ids, scrolling, data, scroll, set_crash = keep_scrolling(driver, tweet_ids, scrolling, data,
                                                                               scroll, last_position, set_crash)
        print('END')
        sleep(1)
        print(scroll)

        print(datetime.strftime(datetime.now(), '%H:%M:%S'))
        refresh_min = datetime.strftime(datetime.strptime('45', '%M'), '%M')
        now_min = datetime.strftime(datetime.now(), '%M')

        print(refresh_min)
        print(now_min)

        if refresh_min == now_min:
            set_crash = False
            tweet_ids.clear
            data = []
            print('60 second sleep')
            sleep(60)
            print('refresh')
            driver.refresh()
            scroll = 0
            last_position = driver.execute_script("return window.pageYOffset;")
        elif set_crash is True:
            set_crash = False
            print('20 second sleep')
            sleep(20)
            print('refresh')
            driver.refresh()
            scroll = 0
            last_position = driver.execute_script("return window.pageYOffset;")
        elif scroll == 3:
            print('5 second sleep')
            sleep(5)
            print('refresh')
            driver.refresh()
            scroll = 0
            last_position = driver.execute_script("return window.pageYOffset;")

driver.quit()
