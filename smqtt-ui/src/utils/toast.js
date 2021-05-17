import { Message, Loading } from 'element-ui'

// 加载中实例
let loadingExamples = null

/**
 * 轻提示工具类
 */
export default {
  info: (msg, callback) => {
    const options = {
      duration: 2000,
      message: '',
      type: 'info',
      customClass: 'toast-index'
    }
    msg && (options.message = msg)
    callback && (options['onClose'] = callback)
    Message(options)
  },

  success: (msg, callback) => {
    const options = {
      duration: 2000,
      message: '',
      type: 'success',
      customClass: 'toast-index'
    }
    msg && (options.message = msg)
    callback && (options['onClose'] = callback)
    Message(options)
  },

  fail: (msg, callback) => {
    const options = {
      duration: 2000,
      message: '',
      type: 'error',
      customClass: 'toast-index'
    }
    msg && (options.message = msg)
    callback && (options['onClose'] = callback)
    Message(options)
  },

  loading: (page, msg) => {
    const options = {
      fullscreen: true,
      lock: true,
      text: ''
    }
    msg && (options.text = msg)
    page.$nextTick(() => {
      loadingExamples = Loading.service(options)
    })
  },

  close: (page) => {
    page.$nextTick(() => {
      loadingExamples && loadingExamples.close()
    })
  }
}
