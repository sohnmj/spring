package wordbook.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wordbook.redis.entity.GetCuponDTO;
import wordbook.redis.service.CuponService;

@RestController
@RequestMapping("/api/v1/cupon")
public class CuponController {

    private CuponService cuponService;
    @Autowired
    public void setCuponService(CuponService cuponService) {
        this.cuponService = cuponService;
    }
    @PostMapping("")
    public ResponseEntity<Boolean> getCupon(@RequestBody GetCuponDTO getCuponDTO) {
        boolean cupon = cuponService.getCupon(getCuponDTO);
        return ResponseEntity.ok(cupon);
    }

}
