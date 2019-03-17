const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

/**
 * 将"2018-05-19T08:04:52.000+0000"这种格式的时间转化为正常格式
 * @param time
 */
const timeFormat = time => {
  var d = new Date(time);

  var year = d.getFullYear();       //年  
  var month = d.getMonth() + 1;     //月  
  var day = d.getDate();            //日  

  var hh = d.getHours();            //时  
  var mm = d.getMinutes();          //分  
  var ss = d.getSeconds();           //秒  

  var clock = year + "/";

  if (month < 10)
    clock += "0";

  clock += month + "/";

  if (day < 10)
    clock += "0";

  clock += day + " ";

  if (hh < 10)
    clock += "0";

  clock += hh + ":";
  if (mm < 10) clock += '0';
  clock += mm + ":";

  if (ss < 10) clock += '0';
  clock += ss;
  return (clock);
}

const dateFormat = date => { 
  const d = new Date(date);
  const month = d.getMonth() + 1;     //月  
  const day = d.getDate();            //日  

  var clock="";
  if (month < 10)
    clock += "0";
  clock += month + "月";

  if (day < 10)
    clock += "0";
  clock += day + "日";
  return (clock);
}

module.exports = {
  formatTime: formatTime,
  timeFormat: timeFormat,
  dateFormat: dateFormat
}
