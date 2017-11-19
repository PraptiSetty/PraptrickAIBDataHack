
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.providers.Yahoo;
import de.fhpotsdam.unfolding.utils.MapUtils;
import java.io.IOException;
import processing.core.PApplet;

public class Map extends PApplet
{
	UnfoldingMap			map;
	private static Location	DUBLIN_LOCATION			= new Location(53.349792f,
			-6.2602963f);
	private static float	MAX_PANNING_DISTANCE	= 10;
	private static int		MIN_ZOOM				= 12;
	private static int		MAX_ZOOM				= 15;
	
	public static void main(String[] args)
	{
		PApplet.main("Map");
	}
	
//	public Map(Location location, float maxPan, int minZoom, int maxZoom)
//	{
//		map = new UnfoldingMap(this, new GoogleMap());
//		map.zoomAndPanTo(location, minZoom);
//		map.setPanningRestriction(location, maxPan);
//		map.setZoomRange(minZoom, maxZoom);
//		map.setTweening(true);
//
//		MapUtils.createDefaultEventDispatcher(this, map);
//	}

	public void settings()
	{
		size(800, 600);
	}

	@SuppressWarnings("deprecation")
	public void setup()
	{
		map = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());
		map.zoomAndPanTo(DUBLIN_LOCATION, MIN_ZOOM);
	//	map.setPanningRestriction(DUBLIN_LOCATION, MAX_PANNING_DISTANCE);
	//	map.setZoomRange(MIN_ZOOM, MAX_ZOOM);
		map.setTweening(true);

		MapUtils.createDefaultEventDispatcher(this, map);

	}

	public void draw()
	{
		map.draw();
	}
	
	public Location getMouseLocation()
	{
		if (map.isHit(mouseX, mouseY))
		{
			Location location = map.getLocation(mouseX, mouseY);
			map.zoomAndPanTo(location, map.getZoomLevel());
			return location;
		} 
		else
		{
			return null;
		}
	}

	public void mouseClicked()
	{		
		try
		{
			Location location = getMouseLocation();
			TemperatureOnMap.mainTemp(location);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
