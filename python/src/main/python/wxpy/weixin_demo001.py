import itchat

itchat.login()

# 拿到好友列表
friends = itchat.get_friends(update=True)[0:]

male = female = other = 0

# 从第二个开始遍历
for i in friends[1:] :
    sex = i["Sex"]
    if sex == 1:
        male += 1
    elif sex == 2 :
        female += 1
    else:
        other += 1

total = len(friends[1:])

print("男生：%d" % male)
print("女生：%d " % female)
print("其他：%d " % other)
