#coding:utf-8
# -*- coding: UTF-8 -*-
from urllib import request
from bs4 import BeautifulSoup as bs

if __name__ == "__main__":
    resp = request.urlopen('https://movie.douban.com/cinema/nowplaying/chengdu/')
    html_data = resp.read().decode('utf-8')
    soup = bs(html_data, 'html.parser')
    playing_movie = soup.find_all('div', id='nowplaying')
    playing_movie_list = playing_movie[0].find_all('li', class_='list-item')
    playing_list = []
    for item in playing_movie_list:
        playing_dict = {}
        playing_dict['id'] = item['data-subject']
        playing_dict['name'] = item['data-title']
        playing_dict['score'] = item['data-score']
        playing_dict['year'] = item['data-release']
        playing_dict['time'] = item['data-duration']
        playing_dict['region'] = item['data-region']
        playing_dict['director'] = item['data-director']
        playing_dict['actors'] = item['data-actors']
        for tag_img_item in item.find_all('img'):
             playing_dict['img_url'] = tag_img_item['src']
        playing_list.append(playing_dict)
        
    print(playing_list)
