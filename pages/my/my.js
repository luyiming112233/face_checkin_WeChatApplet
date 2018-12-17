var app = getApp();
var utils = require('../../utils/util.js')
Page({  
  /**
   * 页面的初始数据
   */
  data: {  
    userInfo: {
      avatarUrl: "",//用户头像
      nickName: "",//用户昵称
    },

    id:'',//方便存在本地的locakStorage  
    response:'' //存取返回数据  
  }, 

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    app.checkLogin();

    if (options.id != null && options.id !=""){
      this.setData({
        id: options.id
      })
    }   
    //执行初始化
    this.dataRefresh("init");
    
  },
  

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
	//执行初始化
	this.dataRefresh("init");
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * ---------------------------------------
   */

  /**
* Img处理函数
*/
my_7_click:function(){

},

/**
* text处理函数
*/
my_8_click:function(e){

},

/**
* Img处理函数
*/
my_12_click:function(){
  wx.navigateTo({
    url: '../personal/personal',
  })
},

/**
* text处理函数
*/
my_13_click:function(e){
  wx.navigateTo({
    url: '../personal/personal',
  })
},

/**
* Img处理函数
*/
my_14_click:function(){

},

/**
* Img处理函数
*/
my_19_click:function(){
  wx.navigateTo({
    url: '../login/login',
  })
},

/**
* text处理函数
*/
my_20_click:function(e){
  wx.navigateTo({
    url: '../login/login',
  })
},

/**
* Img处理函数
*/
my_21_click:function(){

},

/**
* Img处理函数
*/
my_25_click:function(){
  wx.navigateTo({
    url: '../about/about',
  })
},

/**
* text处理函数
*/
my_26_click:function(e){
  wx.navigateTo({
    url: '../about/about',
  })
},

/**
* Img处理函数
*/
my_27_click:function(){

},

/**
* Img处理函数
*/
my_31_click:function(){
  wx.navigateTo({
    url: '../suggest/suggest',
  })
},

/**
* text处理函数
*/
my_32_click:function(e){
  wx.navigateTo({
    url: '../suggest/suggest',
  })
},

/**
* Img处理函数
*/
my_33_click:function(){

},

/**
* Img处理函数
*/
my_37_click:function(){

},

/**
* text处理函数
*/
my_38_click:function(e){

},

/**
* Img处理函数
*/
my_39_click:function(){

},

/**
* Img处理函数
*/
my_43_click:function(){

},

/**
* text处理函数
*/
my_44_click:function(e){

},

/**
* Img处理函数
*/
my_45_click:function(){

},

/**
* Img处理函数
*/
my_48_click:function(){

},

/**
* text处理函数
*/
my_49_click:function(e){

},

/**
* Img处理函数
*/
my_51_click:function(){

},

/**
* text处理函数
*/
my_52_click:function(e){

},

/**
* Img处理函数
*/
my_54_click:function(){

},

/**
* text处理函数
*/
my_55_click:function(e){

},


 
  /**
   * ---------------------------------------
   */

  //刷新数据
  dataRefresh:function(_action) {
    //提交到服务器
    var that = this
    wx.request({
      url: 'https://wc.gotu8.com/wx_server//my.aspx',
      data: {
        action: _action,
        uid: app.data.uid,
        //定义变量---start
	
        //定义变量---end
        id: that.data.id
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data);
        var tmp = res.data;

        if(_action=="init"){
		that.setData({

			response: res
		})
	}

	that.setData({
		
		response: res
        })

	 if (tmp.message != null && tmp.message != "") {
          wx.showToast({
            title: tmp.message,
            icon: 'none',
            duration: 2000
          })
        }
      },
      fail: function (res) {
        console.log(res.data);
        console.log('is failed')
      }
    })
  },

   /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})  

