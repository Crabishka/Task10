import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Logic {

    public List<List<Triangle>> Operation(List<Triangle> list) {

        List<List<Triangle>> result = new ArrayList<List<Triangle>>();

        for (Triangle firstTriangle : list) {
            List<Triangle> temporaryList = new ArrayList<>();  // создаем список для запоминания каждый раз
            for (Triangle secondTriangle : list) {
                if (isTrianglesSimilar(firstTriangle, secondTriangle)) {
                    if (!secondTriangle.isTriangleGrouped) temporaryList.add(secondTriangle);  // проверяем, был ли это треугольник в какой-то группе
                    secondTriangle.isTriangleGrouped = true;
                }

            }
            if (temporaryList != null && temporaryList.size() != 0) result.add(temporaryList);

            //list.removeAll(temporaryList);
            //if (list.size() == 0) break;
            //   temporaryList.clear();

        }


        return result;
    }

    public boolean isTrianglesSimilar(Triangle firstTriangle, Triangle secondTriangle) {
        double[] b = firstTriangle.lengthArray(); // lengthOfFirstTriangle
        double[] c = secondTriangle.lengthArray(); // lengthOfSecondTriangle
        for (int i = 0; i < 3; i++) {
            if (b[i] == 0) return false;
        }
        for (int i = 0; i < 3; i++) {
            if (c[i] == 0) return false;
        }
        // максимально дебильное решение
        if (b[0] / c[0] == b[1] / c[1] && b[0] / c[0] == b[2] / c[2]) return true;
        if (b[0] / c[0] == b[1] / c[2] && b[0] / c[0] == b[2] / c[1]) return true;
        if (b[0] / c[1] == b[2] / c[2] && b[0] / c[1] == b[1] / c[0]) return true;
        if (b[0] / c[1] == b[2] / c[0] && b[0] / c[1] == b[1] / c[2]) return true;
        if (b[0] / c[2] == b[2] / c[0] && b[0] / c[2] == b[1] / c[1]) return true;
        if (b[0] / c[2] == b[2] / c[1] && b[0] / c[2] == b[1] / c[0]) return true;

        return false;
    }


}
