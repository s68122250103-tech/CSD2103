package algorithms;

import java.util.Stack;
import models.Vehicle;

public class AlgorithmB {
    public static boolean removeVehicleRecursive(Stack<Vehicle> stack, String targetPlate) {
        if (stack.isEmpty()) {
            return false;
        }

        Vehicle currentVehicle = stack.pop();

        if (currentVehicle.getLicensePlate().equals(targetPlate)) {
            return true;
        }

        boolean isFound = removeVehicleRecursive(stack, targetPlate);
        stack.push(currentVehicle);
        
        return isFound;
    }
}