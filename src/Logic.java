import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Logic {

    public List<List<Triangle>> Operation(List<Triangle> list) {

        List<List<Triangle>> result = new ArrayList<List<Triangle>>();

        List<Triangle> listOfUsedTriangle = new ArrayList<>();

        for (Triangle firstTriangle : list) {
            List<Triangle> temporaryList = new ArrayList<>();  // создаем список для запоминания каждый раз
            for (Triangle secondTriangle : list) {
                if (firstTriangle.isSimilarTo(secondTriangle)) {
                    if (!listOfUsedTriangle.contains(secondTriangle)) {
                        temporaryList.add(secondTriangle);
                        listOfUsedTriangle.add(secondTriangle);
                    }

                }

            }
            if (temporaryList != null && temporaryList.size() != 0) result.add(temporaryList);

        }


        return result;
    }




}
