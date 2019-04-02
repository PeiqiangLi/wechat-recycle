//index.js
//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    pageNum: 1,
    pageSize: 5,
    nocancel: true,
    imgUrls: [],
    img1: '/images/types/kuzi.png',
    img2: '/images/types/yinliao.png',
    img3: '/images/types/newspaper.png',
    img4: '/images/types/battery.png',
    img5: '/images/types/dianshiji.png',
    img6: '/images/types/ziyuan.png',
    img7: '/images/types/ershouche.png',
    img8: '/images/types/all.png',
    indicatorDots: true,
    autoplay: true,
    interval: 5000,
    duration: 1000,
    circular: true,
    article: [],
    userInfo: {},
    hasUserInfo: false,
    //判断小程序的API，回调，参数，组件等是否在当前版本可用。
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //轮播图处理
  changeIndicatorDots() {
    this.setData({
      indicatorDots: !this.data.indicatorDots
    })
  },
  readArticle (e) {
    wx.navigateTo({
      url: '../article/article?id=' + e.currentTarget.dataset.id,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.request({
      url: app.globalData.url + '/banner/getUsedBanners',
      success: res => {
        res = res.data
        //console.log(res.data);
        var img_url = new Array();
        for (var index in res.data) {
          img_url.push(res.data[index].imgUrl);
          //this.data.imgUrls[index] = res.data[index].imgUrl
        }
        this.setData({
          imgUrls: img_url
        })
        //console.log(this.data.imgUrls);
      }
    })
    wx.request({
      url: app.globalData.url + '/article/getArticles',
      data: {
        pageNum: this.data.pageNum,
        pageSize: this.data.pageSize
      },
      success: res => {
        res = res.data
        console.log(res.data);
        this.setData({
          article: res.data.list
        })
      }
    })
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    console.log(e)
    if (e.detail.userInfo) {
      app.globalData.userInfo = e.detail.userInfo
      this.setData({
        userInfo: e.detail.userInfo,
        hasUserInfo: true
      })
      // 保存用户信息
      wx.request({
        url: app.globalData.url + '/main/getUserInfo',
        method: "POST",
        header: {
          "content-type": "application/json",
          "sessionId": wx.getStorageSync('sessionId'),
          "encryptedData": e.detail.encryptedData,
          "iv": e.detail.iv
        },
        data: e.detail.userInfo,
        success: res => {       
          res = res.data
          console.log(res);
        },
        fail: res => {
          wx.showModal({
            title: '警告',
            content: '获取信息失败请重新进入小程序！！！',
            showCancel: false,
            confirmText: '确认'
          })
        }
      })
      // 
    } else {
      wx.showModal({
        title: '警告',
        content: '请授权后再进入小程序！！！',
        showCancel: false,
        confirmText: '返回授权'
      })
    }
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
    this.sessionId({
      pageNum: 1,
      pageSize: 5
    })
    wx.request({
      url: app.globalData.url + '/article/getArticles',
      data: {
        pageNum: this.data.pageNum,
        pageSize: this.data.pageSize
      },
      success: res => {
        res = res.data
        console.log(res.data);
        this.setData({
          article: res.data.list
        })
      }
    })
    wx.stopPullDownRefresh()
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

  }
})
