// pages/suggest/suggest.js
var helloData = {
  currentWordNumber: 0,
  texts: '请输入至少五个字',
  min: 5,
  max: 200,
}
Page({

  /**
   * 页面的初始数据
   */
  
  data: helloData,

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("here")

  },
  inputs: function (e) {
    var value = e.detail.value;
    //console.log(e.detail.value);
    // 获取输入框内容的长度
    // var len = parseInt(value.length);
    var len = value.length;
    if (len <= this.data.min)
      this.setData({
        texts: "最少五个字"
      })
    else if (len > this.data.min)
      this.setData({
        texts: " "
      })
    if (len > this.data.max)
      return;
    this.setData({
      currentWordNumber: len //当前字数
    });
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
    console.log("onshow")

  },
  formSubmit: function (e) {
    console.log("*****" + e.detail.value.suggest + "****" + e.detail.value.suggest.length)
    if(e.detail.value.suggest.length>=5)
    {
    wx.request({
      url: 'https://www.duliu12.xin:8443/suggest',
      header: { 
        'content-type': 'application/x-www-form-urlencoded',
        'User-Token': wx.getStorageSync('token')
       },
      method: 'POST',
      data: {
      //  'openid': wx.getStorageSync('openid'),
        'suggest': e.detail.value.suggest
      },
      method: 'POST',
      success: function (res) {
        console.log(res);
        wx.showToast({
          title: '提交成功！',
          duration: 1000,
          mask: true
        })
        
      },
      fail: function () {
        wx.showToast({
          title: '提交失败！',
          duration: 1000,
          mask: true
        })
      },
      complete: function () {
        // complete
      }
    })
    }
    else
    wx.showToast({
      title: '字数不足五个',
      image:'/images/images/icon/xx.png',
    })


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
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  
});