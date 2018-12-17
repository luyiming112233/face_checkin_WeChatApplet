var utils = require('../../utils/util.js')
Page({
  upload: function () {
    let that = this;
    wx.chooseImage({
      count: 9, // 默认9 
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有 
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有 
      success: res => {
      wx.showToast({
        title: '正在上传...',
        icon: 'loading',
        mask: true,
        duration: 1000
      })
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片 
        let tempFilePaths = res.tempFilePaths;
        that.setData({
        tempFilePaths: tempFilePaths
      })
        /**
         * 上传完成后把文件上传到服务器
         */ var count = 0;
        for (var i = 0, h = tempFilePaths.length; i < h; i++) {
          //上传文件
          wx.uploadFile({
            url:'http://api.duliu12.xin:8080/'+'file/register',
            filePath: tempFilePaths[i],
              name: 'file',
            header: { 
              "Content-Type": "multipart/form-data",
              'User-Token': wx.getStorageSync('token')
             },
            formData: {
           //   'stuid': wx.getStorageSync('stuid')
            },
          success: function (res) {
            console.log(res);
            console.log(res.data['code']);
            var obj = JSON.parse(res.data);
            console.log(obj.code);
            if (obj.code == 10000) {
              wx.showModal({
                title: '提示',
                content: '注册成功',
                success(res) {
                  if (res.confirm) {
                    console.log('用户点击确定')
                  } else if (res.cancel) {
                    console.log('用户点击取消')
                  }
                }
              })
            } else {
              wx.showModal({
                title: '提示',
                content: '注册失败，请重试',
                success(res) {
                  if (res.confirm) {
                    console.log('用户点击确定')
                  } else if (res.cancel) {
                    console.log('用户点击取消')
                  }
                }
              })
            }
          },
          fail: function (res) {
            wx.hideToast();
            wx.showModal({
              title: '错误提示',
              content: '上传图片失败',
              showCancel: false,
              success: function (res) { }
            })
          }
        });
       
  }

}
    })

    }
 

})