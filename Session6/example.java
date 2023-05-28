package lab8_ex;

import java.util.ArrayList;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;

import DataObjects.DataFloatFloat;
import DataObjects.DataInteger;
import DataObjects.DataREL;
import DataObjects.DataRELQueue;
import DataObjects.DataTransfer;
import DataOnly.FloatFloat;
import DataOnly.REL;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class example {

	public static void main(String[] args) {
		PetriNet pn = new PetriNet();
		pn.PetriNetName = "Test";
		pn.NetworkPort = 1082;

		DataREL ps_i1 = new DataREL();
		ps_i1.SetName("ps_i1");
		pn.PlaceList.add(ps_i1);

		DataRELQueue ps_1 = new DataRELQueue();
		ps_1.SetName("ps_1");
		ps_1.Value.Size = 10;
		//ps_1.SetValue(new REL(1, 1, 1));
		pn.PlaceList.add(ps_1);
		
		DataInteger ps_2 = new DataInteger();
		ps_2.SetName("ps_2");
		ps_2.SetValue(0);
		pn.PlaceList.add(ps_2);
		
		DataInteger ps_3 = new DataInteger();
		ps_3.SetName("ps_3");
		ps_3.SetValue(0);
		pn.PlaceList.add(ps_3);
		
		DataTransfer p_o1 = new DataTransfer();
		p_o1.SetName("ps_o1");
		p_o1.Value = new TransferOperation("localhost", "1080", "p_i1");
		pn.PlaceList.add(p_o1);
		
		DataInteger ps_i2 = new DataInteger();
		ps_i2.SetName("ps_i2");
		pn.PlaceList.add(ps_i2);
		
		DataInteger ps_o2 = new DataInteger();
		ps_o2.SetName("ps_o2");
		pn.PlaceList.add(ps_o2);
		
		/*
		// ----------Constant value to increment p1------------
		DataFloatFloat Const = new DataFloatFloat();
		Const.SetName("Const");
		Const.SetValue(new FloatFloat(2.0f, 2.0f));
		pn.ConstantPlaceList.add(Const);
		*/
		
		// T1 ------------------------------------------------
		PetriTransition ts_1 = new PetriTransition(pn);
		ts_1.TransitionName = "ts_1";
		ts_1.InputPlaceName.add("ps_i1");
		ts_1.InputPlaceName.add("ps_1");
		
		/*
		 * ArrayList<String> IsInput = new ArrayList<String>(); IsInput.add("ps_i1");
		 * IsInput.add("ps_1");
		 */
		Condition T1Ct1 = new Condition(ts_1, "ps_i1", TransitionCondition.NotNull);
		//Condition T1Ct2 = new Condition(ts_1, "ps_1", TransitionCondition.NotNull);
		
		//T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);
		
		GuardMapping grdT1 = new GuardMapping();
		grdT1.condition = T1Ct1;

		grdT1.Activations.add(new Activation(ts_1,"ps_i1" , TransitionOperation.AddElement, "ps_1"));

		ts_1.GuardMappingList.add(grdT1);
		ts_1.Delay = 0;
		pn.Transitions.add(ts_1);

		// T2---------------------------------------------------------
		PetriTransition t2 = new PetriTransition(pn);
		t2.TransitionName = "ts_2";
		t2.InputPlaceName.add("ps_1");
		t2.InputPlaceName.add("ps_2");
		t2.InputPlaceName.add("ps_3");
		
		/*
		 * ArrayList<String> IsInput2 = new ArrayList<String>(); IsInput2.add("ps_i1");
		 * IsInput2.add("ps_2"); IsInput2.add("ps_3");
		 */
		
		ArrayList<String> IsOutput2 = new ArrayList<String>();
		IsOutput2.add("ps_o1");
		IsOutput2.add("ps_3");

		Condition T2Ct1 = new Condition(t2, "ps_1", TransitionCondition.HaveREL);
		Condition T2Ct2 = new Condition(t2, "ps_2", TransitionCondition.NotNull);
		Condition T2Ct3 = new Condition(t2, "ps_3", TransitionCondition.NotNull);
		
		T2Ct2.SetNextCondition(LogicConnector.AND, T2Ct3);
		T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

		GuardMapping grdT2 = new GuardMapping();
		grdT2.condition = T2Ct1;

		grdT2.Activations.add(new Activation(t2, "ps_1", TransitionOperation.SendROverNetwork, "ps_o1"));
		grdT2.Activations.add(new Activation(t2,"ps_1", TransitionOperation.PopElement_R_E, IsOutput2));

		t2.GuardMappingList.add(grdT2);
		t2.Delay = 0;
		pn.Transitions.add(t2);
		
		// ----------------------------------T_3-----------------------------------------

		PetriTransition t3 = new PetriTransition(pn);
		t3.TransitionName = "ts_3";
		t3.InputPlaceName.add("ps_i2");
		
		/*
		 * ArrayList<String> IsOutput3 = new ArrayList<String>(); IsOutput3.add("ps_2");
		 * IsOutput3.add("ps_o2");
		 */
		
		Condition T3Ct1 = new Condition(t2, "ps_i2", TransitionCondition.NotNull);
		
		GuardMapping grdT3 = new GuardMapping();
		grdT3.condition = T3Ct1;
		
		grdT3.Activations.add(new Activation(t3,"ps_i2", TransitionOperation.Move, "ps_2"));
		grdT3.Activations.add(new Activation(t3,"ps_i2", TransitionOperation.Move, "ps_o2"));
		
		t3.GuardMappingList.add(grdT3);
		t3.Delay = 0;
		pn.Transitions.add(t3);
		
		System.out.println("Exp3 started \n ------------------------------");
		pn.Delay = 2000;

		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = pn;
		frame.setVisible(true);

	}

}
