package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.pojo.CarList;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }

    //购买车辆
    @GetMapping("buyCar/{carName}/{carType}/{carSeries}/{count}")
    public JSONResult buyCar(@PathVariable String carName,@PathVariable String carType,@PathVariable String carSeries,@PathVariable int count) {
         Boolean result = carService.buyCar(carName,carType,carSeries,count);
         if (result){
            return JSONResult.ok();
        }else {
            return JSONResult.errorMsg("车库数量不足");
        }
    }

    //模糊查询分页
    @GetMapping("findByCarNames/{carName}/{begin}/{end}")
    public JSONResult findByCarName(@PathVariable String carName,@PathVariable int begin,@PathVariable int end) {
        List<Car> cars = carService.findByCarName(carName);
        List<Car> cars1 = new ArrayList<>();
        for (int i = 0; i <cars.size() ; i++) {
            if (i>=(begin-1) && i<=(end-1))
                cars1.add(cars.get(i));
        }
        return JSONResult.ok(cars1);
    }
}
