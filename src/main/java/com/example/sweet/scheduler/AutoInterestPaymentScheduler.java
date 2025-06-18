package com.example.sweet.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.repository.dto.PhieuTraLaiDTO;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.services.GiaoDich.PhieuTraLaiService;

import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Instant;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@AllArgsConstructor
public class AutoInterestPaymentScheduler {
    
    private static final Logger log = LoggerFactory.getLogger(AutoInterestPaymentScheduler.class);
    
    private final PhieuGuiTienRepository phieuGuiTienRepository;
    private final PhieuTraLaiService phieuTraLaiService;

    @Scheduled(cron = "0 0 0 * * *") // Run at midnight every day
    public void autoCreatePhieuTraLai() {
        // Get all active PhieuGuiTien
        List<PhieuGuiTien> activePhieuGuiTiens = phieuGuiTienRepository
            .findByTrangThaiMaTrangThai("unsettled");

        LocalDate today = LocalDate.now();

        for(PhieuGuiTien phieuGuiTien : activePhieuGuiTiens) {
            try {
                if(shouldPayInterestToday(phieuGuiTien, today)) {
                    createInterestPayment(phieuGuiTien);
                }
            } catch(Exception e) {
                // Log error but continue processing other phieuGuiTien
                log.error("Error processing interest payment for PhieuGuiTien ID: " 
                    + phieuGuiTien.getPhieuGuiTienID(), e);
            }
        }
    }

    private boolean shouldPayInterestToday(PhieuGuiTien pgt, LocalDate today) {
        LocalDate ngayGuiDate = pgt.getNgayGuiTien()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
        LocalDate ngayDaoHanDate = pgt.getNgayDaoHan()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
            
        String tanSuat = pgt.getChiTietQuyDinhLaiSuat()
            .getTanSuatNhanLai()
            .getTenTanSoNhanLai();

        return switch(tanSuat.toLowerCase()) {
            case "đầu kỳ hạn" -> today.isEqual(ngayGuiDate);
            case "cuối kỳ hạn" -> today.isEqual(ngayDaoHanDate);
            case "hàng tháng" -> today.getDayOfMonth() == ngayGuiDate.getDayOfMonth();
            case "hàng quý" -> today.getDayOfMonth() == ngayGuiDate.getDayOfMonth() 
                && (today.getMonthValue() - ngayGuiDate.getMonthValue()) % 3 == 0;
            default -> false;
        };
    }

    private void createInterestPayment(PhieuGuiTien phieuGuiTien) {
        PhieuTraLaiDTO dto = new PhieuTraLaiDTO();
        dto.setPhieuGuiTienID(phieuGuiTien.getPhieuGuiTienID());
        phieuTraLaiService.createPhieuTraLai(dto);
    }
}
