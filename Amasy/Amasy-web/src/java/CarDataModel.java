
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

public class CarDataModel extends ListDataModel<Car> implements SelectableDataModel<Car>, Serializable {

    public CarDataModel() {
    }

    public CarDataModel(List<Car> data) {
        super(data);
    }

    @Override
    public Car getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  

        List<Car> cars = (List<Car>) getWrappedData();

        for (Car car : cars) {
            if (car.getModel().equals(rowKey)) {
                return car;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Car car) {
        return car.getModel();
    }
}
