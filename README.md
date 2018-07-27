# cnkispider
知网爬虫，根据搜索搜索基金号爬取文献名
## 爬虫思路  
任务是拿基金号去知网上爬取搜索结果，刚开始发现通过[知网高级检索](http://nvsm.cnki.net/kns/brief/result.aspx?dbprefix=SCDB&crossDbcodes=CJFQ,CDFD,CMFD,CPFD,IPFD,CCND,CCJD)搜索以后是渲染网页，而且有极高的反爬虫机制，最后无法获得搜索内容。最后通过不懈努力找到一个原始版的搜索页面能完成特定的搜索跳转，但是其中的url有反爬虫所以无法实现跳转，只能拿到搜索的名字。 
   
新版搜索页面：  
![新版搜索页面](http://wx1.sinaimg.cn/mw690/0060lm7Tly1ftohyaaplsj30z80ngjw1.jpg)  
  

老版搜索页面：  
![老版搜索页面](http://wx2.sinaimg.cn/mw690/0060lm7Tly1ftoi03b80nj30sf0fkdkg.jpg)

## 程序结构
![](http://wx4.sinaimg.cn/mw690/0060lm7Tly1ftoi7ltt1ej308308e0ss.jpg
)  
__connection__	：用来连接网址，处理数据最后调用WriteTxt写入文件  
**file**：用来对文件进行操作  
**main**：有main函数，程序起点