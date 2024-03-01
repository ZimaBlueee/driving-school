import request from '@/utils/request'

export function gengerateQrcode() {
  return request({
    url: '/Qrcode/generateQrcode',
    method: 'get',
    responseType: 'blob',
  })
}
