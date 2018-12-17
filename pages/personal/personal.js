var app = getApp()
var utils = require('../../utils/util.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  //载入用户信息，把后端的个人信息显示在前端
  zairu: function () {
    var that = this
    console.log('载入')
    wx.request({
      url: utils.webUrl+'studentinfo',
      header: { 
       //    'content-type': 'application/x-www-form-urlencoded',
           'User-Token': wx.getStorageSync('token')
       },

      data: {
      },
      method: 'POST',
      success: function (res) {
        console.log(res.data)
        console.log("返回是：" + res.data.data)// 操作json数据

        that.setData({
          'myinfo.no': res.data.data.studentId,
          'myinfo.name': res.data.data.name,
          'myinfo.classname': res.data.data.classname,
          'myinfo.departmentname': res.data.data.departname,
          'myinfo.email': res.data.data.email
        })
        console.log('***********')
      },
      fail: function () {
        // fail
      },
      complete: function () {
        // complete
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('here')
    this.zairu()

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


})