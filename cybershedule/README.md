import com.github.cyborgmg.cybershedule.Shedulable;
import com.github.cyborgmg.cybershedule.Shedule;

public class SheduleMain implements Shedulable {
	
	Shedule shedule;

	public static void main(String[] args) {
		
		SheduleMain sheduleMain = new SheduleMain();
		
		sheduleMain.shedule = new Shedule(sheduleMain, 1);
		sheduleMain.shedule.start("Thread-SheduleMain");
		
	}

	@Override
	public void execute() {
		
		System.out.println("*********************execute ");
		
	}

}