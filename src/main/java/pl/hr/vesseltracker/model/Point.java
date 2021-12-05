package pl.hr.vesseltracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point
{
    private final double x;
    private final double y;
    private final VesselModel vesselModel;

}
