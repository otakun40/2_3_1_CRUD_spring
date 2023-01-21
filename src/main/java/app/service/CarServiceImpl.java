package app.service;

import app.dao.CarDao;
import app.dao.CarDaoImpl;
import app.model.Car;
import java.util.List;

public class CarServiceImpl implements CarService{
    private final CarDao carDao = new CarDaoImpl();
    @Override
    public List<Car> getListByCount(int count) {
        return carDao.getListByCount(count);
    }
}
