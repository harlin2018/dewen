const pattern = {
  // http://emailregex.com/
  email: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
  url: new RegExp('^(?!mailto:)(?:(?:http|https|ftp)://|//)(?:\\S+(?::\\S*)?@)?(?:(?:(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[0-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]+-?)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]+-?)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))|localhost)(?::\\d{2,5})?(?:(/|\\?|#)[^\\s]*)?$', 'i'),
  hex: /^#?([a-f0-9]{6}|[a-f0-9]{3})$/i,
  pot2: /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/,
  idNum: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
  number: /^[0-9]*[1-9][0-9]*$/,
  numberSize: /^(\-|\+)?\d+(\.\d+)?$/,
  phone: /^1\d{10}$/,
  phones: /^[+]{0,1}(\d){1,3}([-]?(\d){1,12})+$/
}
const check = {
  integer: function integer(value) {
    return check.number(value) && parseInt(value, 10) === value
  },
  float: function float(value) {
    return check.number(value) && !check.integer(value)
  },
  array: function array(value) {
    return Array.isArray(value)
  },
  regexp: function regexp(value) {
    if (value instanceof RegExp) {
      return true;
    }
    try {
      return !!new RegExp(value)
    } catch (e) {
      return false
    }
  },
  date: function date(value) {
    return typeof value.getTime === 'function' && typeof value.getMonth === 'function' && typeof value.getYear === 'function'
  },
  number: function number(value) {
    if (typeof value === 'number') return true
    return typeof value === 'string' && value.match(pattern.number)
  },
  numberSize: function number(value, rule) {
    if (typeof value === 'number') return value >= rule.min && value <= rule.max
    return typeof value === 'string' && value.match(pattern.numberSize) && value >= rule.min && value <= rule.max
  },
  numbers: function number(value, rule) {
    if (!rule.required&&(value==undefined||value=='')) {
      return true
    }
    if (typeof value === 'number') return value.toString().length >= (rule.min || 0) && value.toString().length <= rule.max
    return typeof value === 'string' && value.match(pattern.number) && value.length >= (rule.min || 0) && value.length <= rule.max
  },
  method: function method(value) {
    return typeof value === 'function'
  },
  email: function email(value) {
    return typeof value === 'string' && !!value.match(pattern.email) && value.length < 255
  },
  url: function url(value) {
    return typeof value === 'string' && !!value.match(pattern.url)
  },
  hex: function hex(value) {
    return typeof value === 'string' && !!value.match(pattern.hex)
  },
  match: function match(value, rule, data) {
    return data.temp.hashPassword === data.temp.confirmPassword
  },
  custom: function custom(value, rule,data) {
     value.match(data)
  },
}

const validator = function(type, msg, data) {
  return (rule, value, callback) => {
    if (check[type](value, rule, data)) {
      return callback()
    } else {
      return callback(new Error(msg))
    }
  }
}

export default {
  len: (max, min) => { return { required: true, trigger: 'change', max: max, min: min || 1, message: max === (min || 1) ? `请输入${max}个字符` : `请输入${min || 1}到${max}个字符` } },
  numbers: (max, min) => { return { required: true, trigger: 'change', validator: validator('numbers', max === (min || 1) ? `请输入${max}个数字` : `请输入${min || 1}到${max}个数字`), max: max, min: min || 1 } },
  numberSize: (min, max) => { return { required: true, trigger: 'change', validator: validator('numberSize', `请输入${min}到${max}范围的数字`), min: min, max: max } },
  password: (data) => { return { required: true, trigger: 'change', validator: validator('match', '两次密码输入不一致', data) } },
  number: { required: true, validator: validator('number', '请输入数字格式'), trigger: 'change' },
  date: { type: 'date', required: true, trigger: 'change', message: '请输入正确的日期格式' },
  url: { type: 'url', required: true, trigger: 'change', message: '请输入正确的地址格式' },
  email: { type: 'email', required: true, trigger: 'change', message: '请输入正确的邮件格式' },
  hex: { type: 'hex', required: true, trigger: 'change', message: '请输入正确的数值格式' },
  float: { type: 'float', required: true, trigger: 'float', message: '请输入正确的二进制格式' },
  phone: { required: true, pattern: pattern.phone, message: '请输入正确的手机号', trigger: 'change' },
  phones: { required: true, pattern: pattern.phones, message: '请输入正确的号码', trigger: 'change' },
  required: { required: true, message: '不能为空', trigger: 'change' },
  choose: { required: true, message: '请选择必选项', trigger: 'change' },
  idNum: { required: true, pattern: pattern.idNum, message: '请输入正确的身份证号码', trigger: 'change' },

  $len: (max) => { return { trigger: 'change', max: max, message: `最大输入${max}个字符` } },
  $numbers: (max) => { return { trigger: 'change', validator: validator('numbers', `最大输入${max}个数字`), max: max } },
  $password: (data) => { return { trigger: 'change', validator: validator('match', '两次密码输入不一致', data) } },
  $number: { validator: validator('number', '请输入数字格式'), trigger: 'change' },
  $date: { type: 'date', trigger: 'change', message: '请输入正确的日期格式' },
  $url: { type: 'url', trigger: 'change', message: '请输入正确的地址格式' },
  $email: { type: 'email', trigger: 'change', message: '请输入正确的邮件格式' },
  $float: { type: 'float', trigger: 'float', message: '请输入正确的二进制格式' },
  $hex: { type: 'hex', trigger: 'change', message: '请输入正确的二进制格式' },
  $phone: { pattern: pattern.phone, message: '请输入正确的手机号', trigger: 'change' },

  $custom: (ruleType) => { return { trigger: 'change', validator: validator('numbers', '请输入正确格式',ruleType) } },
}
