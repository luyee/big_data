#coding:utf-8

import warnings
warnings.filterwarnings("ignore")
import jieba    #分词包
import numpy    #numpy计算包
import codecs   #codecs提供的open方法来指定打开的文件的语言编码，它会在读取的时候自动转换为内部unicode
import re
import pandas as pd
import matplotlib.pyplot as plt
from urllib import request
from bs4 import BeautifulSoup as bs
import urllib.parse

# %matplotlib inline

import matplotlib
matplotlib.rcParams['figure.figsize'] = (10.0, 5.0)
from wordcloud import WordCloud#词云包

#分析网页函数
def getNowPlayingMovie_list():
    resp = request.urlopen('https://movie.douban.com/nowplaying/hangzhou/')
    html_data = resp.read().decode('utf-8')
    soup = bs(html_data, 'html.parser')
    nowplaying_movie = soup.find_all('div', id='nowplaying')
    nowplaying_movie_list = nowplaying_movie[0].find_all('li', class_='list-item')
    nowplaying_list = []
    for item in nowplaying_movie_list:
        nowplaying_dict = {}
        nowplaying_dict['id'] = item['data-subject']
        for tag_img_item in item.find_all('img'):
            nowplaying_dict['name'] = tag_img_item['alt']
            nowplaying_list.append(nowplaying_dict)
    return nowplaying_list


def getMovieIdByName():
    name = input("输入电影名：")
    # name = '钢铁侠'
    name = '豆瓣  ' + name
    name = urllib.parse.quote(name)
    nameUrl = 'http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=' + name + '&oq=%25E8%25B1%2586%25E7%2593%25A3%2520%25E8%259C%2598%25E8%259B%259B%25E4%25BE%25A0&rsv_pq=82798019000115c6&rsv_t=5b37%2FF78vjLc%2Bcj34VNVYT29udrj06M2q0ju5rlYNRUMjXb7VSXy3gaELzE&rqlang=cn&rsv_enter=0'
    # print(nameUrl)
    html = request.urlopen(nameUrl)
    html_data = html.read().decode('utf-8')
    # print(html_data)
    soup = bs(html_data, 'html.parser')
    movie_a_lits = soup.find_all('a', class_='c-showurl')
    href = movie_a_lits[0].get('href')
    # 爬去豆瓣的信息
    html = request.urlopen(href)
    html_data = html.read().decode('utf-8')
    soup = bs(html_data, 'html.parser')
    a = soup.find_all('a', class_='lnk-sharing')
    # print(a[0].get('data-object_id'))
    movie_name = a[0].get('data-name')
    print('为你搜索的是《 ' + movie_name + '》的电影评论')
    return a[0].get('data-object_id')

#爬取评论函数
def getCommentsById(movieId, pageNum):
    eachCommentList = []
    if pageNum>0:
        start = (pageNum-1) * 20
    else:
        return False
    requrl = 'https://movie.douban.com/subject/' + movieId + '/comments' +'?' +'start=' + str(start) + '&limit=20'
    print(requrl)
    resp = request.urlopen(requrl)
    html_data = resp.read().decode('utf-8')
    soup = bs(html_data, 'html.parser')
    comment_div_lits = soup.find_all('div', class_='comment')
    for item in comment_div_lits:
        if item.find_all('p')[0].string is not None:
            eachCommentList.append(item.find_all('p')[0].string)
    return eachCommentList

def main():
    #循环获取电影的前10页评论
    commentList = []
    # NowPlayingMovie_list = getNowPlayingMovie_list()
    movieId = getMovieIdByName()
    for i in range(10):
        num = i + 1
        commentList_temp = getCommentsById(movieId, num)
        # commentList_temp = getCommentsById('27133303', num)
        commentList.append(commentList_temp)

    #将列表中的数据转换为字符串
    comments = ''
    for k in range(len(commentList)):
        comments = comments + (str(commentList[k])).strip()

    #使用正则表达式去除标点符号
    pattern = re.compile(r'[\u4e00-\u9fa5]+')
    filterdata = re.findall(pattern, comments)
    cleaned_comments = ''.join(filterdata)

    #使用结巴分词进行中文分词
    segment = jieba.lcut(cleaned_comments)
    words_df=pd.DataFrame({'segment':segment})

    #去掉停用词
    stopwords=pd.read_csv("stopwords.txt",index_col=False,quoting=3,sep="\t",names=['stopword'], encoding='utf-8')#quoting=3全不引用
    words_df=words_df[~words_df.segment.isin(stopwords.stopword)]

    #统计词频
    words_stat=words_df.groupby(by=['segment'])['segment'].agg({"计数":numpy.size})
    words_stat=words_stat.reset_index().sort_values(by=["计数"],ascending=False)

    #用词云进行显示
    wordcloud=WordCloud(font_path="simhei.ttf",background_color="white",max_font_size=80,width=800,height=400)
    word_frequence = {x[0]:x[1] for x in words_stat.head(1000).values}

    word_frequence_list = []
    for key in word_frequence:
        temp = (key,word_frequence[key])
        word_frequence_list.append(temp)

    wordcloud=wordcloud.fit_words(word_frequence)
    plt.imshow(wordcloud)
    plt.axis("off")
    plt.show()

#主函数
if __name__ == '__main__':
    main()
    # getMovieIdByName()