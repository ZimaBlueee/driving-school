package com.ruoyi.drivingSchool.controller;

import com.ruoyi.drivingSchool.service.QrcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : 齐马的作品
 * @version : 1.0
 * @date : 2024-02-29 13:59
 * @description:
 */
@RestController
@RequestMapping("/Qrcode")
public class QrcodeController {

    @Autowired
    private QrcodeService qrcodeService;

    @GetMapping("/generateQrcode")
    public void generateQrcode(HttpServletResponse response) {
        qrcodeService.generateQrcode(response);
    }

    @GetMapping("/checkQrcode")
    public String checkQrcode(String qrcode) {
        return qrcodeService.checkQrcode(qrcode);
    }
}
