// pages/index/index.js
var utils = require('../../utils/util.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    longitude: 0,
    latitude: 0,
    circles: [],
    signInstanceId:0,
    nowlo:0,
    nowla:0
  },
  bindcontroltap: function (e) {
    switch (e.controlId) {
      case 1:
        this.movetoCenter();
        break;
      case 2:
        site(this);
        break;
     case 4:
        warn();
        break;
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(options.id!=null)
   this.setData({
     signInstanceId:options.id,
   })
   else{ 
     console.log("id不存在");
     wx.navigateBack({
   })
   }
   console.log("id是",this.data.signInstanceId)
   var that=this;
   wx.request({
     url: utils.webUrl+'/student/signLocation/'+that.data.signInstanceId,
     header:{
       'content-type': "application/x-www-form-urlencoded",
       'User-Token': wx.getStorageSync('token')
     },
     data:{
      
     },
     success(res){
     //  console.log(res.data.data.longitude.constructor)
      that.setData({
       // longitude:res.data.data.longitude,
      //  latitude:res.data.data.latitude,
       // radius:res.data.data.radius,
        circles: [{
          latitude: res.data.data.latitude,
          longitude: res.data.data.longitude,
          color: '#FF0000DD',
          fillColor: '#7cb5ec88',
          radius: res.data.data.radius,
          strokeWidth: 1


        }],
        markers: [{
          id: "10",
          latitude: res.data.data.latitude,
          longitude: res.data.data.longitude,
          width: 20,
          height: 30,
          iconPath: "/images/marker.png",
          title: "定位中心"

        }]
      })
     }
   })
    wx.getLocation({
      type: 'gcj02', 
      success: (res) => {
       // console.log(res.longitude.constructor)
        this.setData({
          
         longitude: res.longitude,
          latitude: res.latitude,

         /* circles: [{
            latitude: res.latitude,
            longitude: res.longitude,
            color: '#FF0000DD',
            fillColor: '#7cb5ec88',
            radius: 40,
            strokeWidth:1
    

          }],*/
          


    

        })


      },
    })
    wx.getSystemInfo({
      success: (res) => {
        this.setData({
          controls: [{
            id: 1,
            iconPath: "/images/location.png",
            position: {
              width: 50,
              height: 50,
              left: res.windowWidth-70,
              top: res.windowHeight-85 
            },
            clickable: true
          }, {
            id: 2,
            iconPath: "/images/use.png",
            position: {
              width: 100,
              height: 100,
              left: res.windowWidth / 2 - 50,
              top: res.windowHeight - 105
            },
            clickable: true
          }, {
              id: 4,
              iconPath: "/images/warn.png",
              position: {
                width: 50,
                height: 50,
                left: 20,
                top: res.windowHeight - 85
              },
              clickable: true
            }]
        })
      },
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  movetoCenter: function () {
    this.mapctx.moveToLocation();
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.mapctx = wx.createMapContext("map");
    this.movetoCenter();
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
  moveToLocation: function () {

    this.mapctx.moveToLocation()

  }

  
})

function chooseimage(id) {
  var that = this;
 // console.log("that:",this)
 // var id=that.data.signInstanceId;
  wx.chooseImage({
    count: 1, // 默认9  
    sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有  
    sourceType: ['camera'], // 可以指定来源是相册还是相机，默认二者都有  
    success: function (res) {
      // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片  
      var tempFilePaths = res.tempFilePaths;
      upload(that, tempFilePaths,id);
    }
  })

}

function upload(page, path,id) {
  wx.showToast({
    icon: "loading",
    title: "正在上传"
  }),
    wx.uploadFile({
      url: utils.webUrl+"file/upload/"+id,
      filePath: path[0],
      name: 'file',
      header: { 
        "Content-Type": "multipart/form-data" ,
        'User-Token': wx.getStorageSync('token')
        },
      formData: {
      //  'stuid': wx.getStorageSync('stuid')
      },
      success: function (res) {
        console.log(res);
        console.log(res.data['code']);
        var obj = JSON.parse(res.data);
        console.log(obj.code);
        if (obj.code == 10000) {
          wx.showModal({
            title: '提示',
            content: '签到成功',
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
            content: '签到失败',
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
      fail: function (e) {
        console.log(e);
        wx.showModal({
          title: '提示',
          content: '上传失败',
          showCancel: false
        })
      },
      complete: function () {
        wx.hideToast();  //隐藏Toast
      }
    })
}


function Localavailable()
{




}
/*单位m*/
function juli(lat1, lng1, lat2, lng2) {
  console.log(lat1, lng1, lat2, lng2)
  var radLat1 = lat1 * Math.PI / 180.0;
  var radLat2 = lat2 * Math.PI / 180.0;
  var a = radLat1 - radLat2;
  var b = lng1 * Math.PI / 180.0 - lng2 * Math.PI / 180.0;
  var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
  s = s * 6378137.0;
  s = Math.round(s * 10000.0) / 10000.0;
  console.log(s);
  return s
} 

function warn()
{
  wx.showModal({
    title: '使用说明',
    content: '本程序为人脸识别签到程序，需要在红色圆圈内方能使用屏幕正下方照相功能进行人脸识别，人脸对比成功后将会提示签到成功，并更新系统信息',
    success: function (res) {
      if (res.confirm) {
        console.log('用户点击确定')
      } else if (res.cancel) {
        console.log('用户点击取消')
      }
    }
  });}

function site(page) {
 // var that = this;
 // console.log("zheci",page)
  wx.getLocation({
    type: 'gcj02',
    success: (res) => {
      page.setData({
        nowlo: res.longitude,
        nowla: res.latitude,
      })
/*0.0296=1.5个阶梯教室的距离*/
     /* if (Number(juli(page.data.nowlo, page.data.nowla, page.data.longitude, page.data.latitude))> 40) {
        wx.showModal({
          title: '温馨提示',
          content: '位置不能离你大于40米',
          success: function (res) {
            if (res.confirm) {
              console.log('用户点击确定')
            } else if (res.cancel) {
              console.log('用户点击取消')
            }
          }
        });
      } else {*/
        chooseimage(page.data.signInstanceId);
    //  }

    },
  })

 



}