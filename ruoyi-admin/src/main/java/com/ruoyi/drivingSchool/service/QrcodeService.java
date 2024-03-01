package com.ruoyi.drivingSchool.service;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;

/**
 * @author : 齐马的作品
 * @version : 1.0
 * @date : 2024-02-29 14:00
 * @description:
 */
@Service
public class QrcodeService {

    @Autowired
    private SysUserMapper userMapper;

    public void generateQrcode(HttpServletResponse response) {
        try {
            SysUser user = SecurityUtils.getLoginUser().getUser();
            Long userId = user.getUserId();

            byte[] bytes = QrCodeUtil.generatePng(userId.toString(), initQrConfig());
            // 返回字节流
            response.setContentType("image/png");
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
//            log.error("发生错误！ {}！", e.getMessage());
        }
    }

    private static QrConfig initQrConfig() {
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.BLACK.getRGB());
//        // 设置前景色，既二维码颜色（青色）
//        config.setForeColor(Color.CYAN.getRGB());
//        // 设置背景色（灰色）
//        config.setBackColor(Color.GRAY.getRGB());
        // 高纠错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);
//        ClassPathResource classPathResource = new ClassPathResource("log/shdq.jpg");
//        try {
//            InputStream inputStream = classPathResource.getInputStream();
//            config.setImg(ImageIO.read(inputStream));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return config;
    }

    public String checkQrcode(String qrcode) {
        if (StringUtils.isEmpty(qrcode)) {
            throw new ServiceException("二维码不能为空！");
        }

        SysUser sysUser = userMapper.selectUserById(Long.parseLong(qrcode));

        if (sysUser == null) {
            throw new ServiceException("二维码非法！");
        }

        return "ok";
    }
}
