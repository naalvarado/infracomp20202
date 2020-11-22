import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

public class MonitorCPU extends Thread {
	
	private Respuesta re;
	
	public MonitorCPU(Respuesta r) {
		re = r;
	}
	
	public void run() {
		while(!re.getTer()) {
			double load = getSystemCpuLoad();
			System.out.println(load);
			try {
				TimeUnit.MINUTES.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public double getSystemCpuLoad() {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name;
		AttributeList list = null;
		try {
			name = ObjectName.getInstance("java.lang:type=OperatingSystem");
			list = mbs.getAttributes(name, new String[] {"SystemCpuLoad"});
		} catch (MalformedObjectNameException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReflectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(list.isEmpty()) {
			return Double.NaN;
		}
		
		Attribute att = (Attribute)list.get(0);
		Double value = (Double)att.getValue();
		
		if(value == -1.0) {
			return Double.NaN;
		}
		return ((int)(value*1000) / 10.0);
	}

}
