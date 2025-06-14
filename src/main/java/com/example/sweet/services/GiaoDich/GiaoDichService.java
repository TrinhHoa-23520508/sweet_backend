package com.example.sweet.services.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;
import com.example.sweet.database.repository.GiaoDich.LichSuGiaoDich_TKTTRepository;
import com.example.sweet.database.repository.TaiKhoan.TaiKhoanThanhToanRepository;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_TKTT;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GiaoDichService {
    private GiaoDichRepository giaoDichRepo;
    private LichSuGiaoDich_TKTTRepository lichSuRepo;
    private TaiKhoanThanhToanRepository taiKhoanRepo;

    public Iterable<GiaoDich> findAll() {
        return giaoDichRepo.findAll();
    }

    public GiaoDich createGiaoDich(GiaoDich giaoDich) {

        var taiKhoanNguon = taiKhoanRepo.findById(Math.toIntExact(giaoDich.getTaiKhoanNguon())).orElseThrow();
        var taiKhoanDich = taiKhoanRepo.findById(Math.toIntExact(giaoDich.getTaiKhoanDich())).orElseThrow();

        if (taiKhoanNguon.getSoDu() < giaoDich.getSoTienGiaoDich())
            throw new IllegalStateException("Số dư không đủ");

        taiKhoanRepo.save(taiKhoanNguon);
        taiKhoanRepo.save(taiKhoanDich);

        var insertedGiaoDich = giaoDichRepo.save(giaoDich);

        lichSuRepo.save(new LichSuGiaoDich_TKTT(
                0,
                taiKhoanNguon,
                insertedGiaoDich,
                taiKhoanNguon.getSoDu()));
        lichSuRepo.save(new LichSuGiaoDich_TKTT(
                0,
                taiKhoanDich,
                insertedGiaoDich,
                taiKhoanDich.getSoDu()));

        taiKhoanNguon.setSoDu(taiKhoanNguon.getSoDu() - giaoDich.getSoTienGiaoDich());
        taiKhoanDich.setSoDu(taiKhoanDich.getSoDu() + giaoDich.getSoTienGiaoDich());

        taiKhoanRepo.save(taiKhoanNguon);
        taiKhoanRepo.save(taiKhoanDich);

        return insertedGiaoDich;
    }

    // this should either delete or create, no update i think?
    public void cancelGiaoDich(Long id) {
        var giaoDich = giaoDichRepo.findById(id).orElseThrow();

        cancelGiaoDich(giaoDich);
    }

    // Please fill out all field before using this
    public void cancelGiaoDich(GiaoDich giaoDich) {
        var taiKhoanNguon = taiKhoanRepo.findById(Math.toIntExact(giaoDich.getTaiKhoanNguon())).orElseThrow();
        var taiKhoanDich = taiKhoanRepo.findById(Math.toIntExact(giaoDich.getTaiKhoanDich())).orElseThrow();

        taiKhoanNguon.setSoDu(taiKhoanNguon.getSoDu() + giaoDich.getSoTienGiaoDich());
        // Negate that bitch
        taiKhoanDich.setSoDu(taiKhoanDich.getSoDu() - giaoDich.getSoTienGiaoDich());

        lichSuRepo.deleteByTaiKhoanAndGiaoDich(taiKhoanNguon, giaoDich);
        lichSuRepo.deleteByTaiKhoanAndGiaoDich(taiKhoanDich, giaoDich);
        giaoDichRepo.delete(giaoDich);

        taiKhoanRepo.save(taiKhoanNguon);
        taiKhoanRepo.save(taiKhoanDich);

    }
}
