package main

import "fmt"

func main() {
	const STR4  = "test"
	//var flag bool = true
	var test = 123
	var str1,str2,str3 = "test","test2","test3"
	str1 = "test1"
	fmt.Println(str1,str2,str3,test,STR4)

	fmt.Printf("值为: %d \n",test)


	var l int = 5
	var w = 6
	fmt.Println(l * w)

	if l <= 5 {
		fmt.Printf("值为：%d \n",l)
	}


	var a int = qqq(l,w)

	fmt.Println(a)

}

func qqq(str ,str1 int) int {
	return str+str1
}
