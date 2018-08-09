import itchat
import re
import jieba
import os
import numpy
import matplotlib.pyplot as plt
from wordcloud import WordCloud, ImageColorGenerator
import PIL.Image as Image

itchat.login()

friends = itchat.get_friends(update=True)[0:]
tList = []

for i in friends:
    sign = i["Signature"].strip().replace("span", "").replace("class", "").replace("emoji", "")
    rep = re.compile("1f\d.+")
    sign = rep.sub("",sign)
    tList.append(sign)

text = "".join(tList)

wordList = jieba.cut(text,cut_all=True)
wl_space_split = " ".join(wordList)

d = os.path.dirname(__file__)
alice_coloring = numpy.array(Image.open(os.path.join(d, "123.jpg")))
my_wordcloud = WordCloud(background_color="white", max_words=2000, mask=alice_coloring,
                         max_font_size=40, random_state=42,
                         font_path='arialuni.ttf').generate(wl_space_split)

image_colors = ImageColorGenerator(alice_coloring)
plt.imshow(my_wordcloud.recolor(color_func=image_colors))
plt.imshow(my_wordcloud)
plt.axis("off")
plt.show()

