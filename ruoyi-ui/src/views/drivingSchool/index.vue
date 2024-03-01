<template>
  <div>
    <div style="text-align: center;">
      <p>驾校通行码</p>
      <img :src="verifycode" alt class="QRcode"/>
    </div>
  </div>
</template>

<script>
import {gengerateQrcode} from "@/api/system/qrcode";

export default {
  name: "Qrcode",
  data() {
    return {
      verifycode: '',
    };
  },
  created() {
    this.onSubmit();
  },
  methods: {
    onSubmit() {
      gengerateQrcode()
        .then(res => {
          console.log(123)
          console.log(res)
          // 将响应的二进制数据转换为Blob对象
          const blob = new Blob([res], {type: 'image/png'});
          // 创建一个指向该Blob对象的URL，并设置为img标签的src属性
          this.verifycode = window.URL.createObjectURL(blob);
          console.log(2222)
          console.log(this.verifycode)
        })
        .catch(error => {
          console.error('Error generating QR code:', error);
          this.$message({
            showClose: true,
            message: 'Error generating QR code',
            type: 'error'
          });
        });
    }


  }
};
</script>
