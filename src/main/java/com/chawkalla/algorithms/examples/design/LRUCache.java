package com.chawkalla.algorithms.examples.design;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.chawkalla.algorithms.bean.Entry;
import com.chawkalla.algorithms.ds.LinkedList;

public class LRUCache {

	private int capacity=0;
	private HashMap<Integer, Entry<Integer, Integer>> map=new HashMap<Integer, Entry<Integer, Integer>>(); //entries are entry in linkedlist
	private LinkedList<Integer, Integer> list=new LinkedList<Integer, Integer>(); //list will have entries of type int, int
	//private java.util.LinkedList<LNode> list=new java.util.LinkedList<LNode>();

	public LRUCache(int capacity) {
		this.capacity=capacity;
	}

	public int get(int key) {
		int value=-1;
		if(map.containsKey(key)){
			Entry<Integer, Integer> e=map.get(key);
			if(e!=null){
				value=e.data;
				//move entry to the end.
				list.removeAndMoveToLast(e);
				/*list.remove(e);
				list.add(e);*/
			}
		}
		return value;
	}

	public void set(int key, int value) {

		if(capacity==0)
			return;
		
		if(map.containsKey(key)){
			Entry<Integer, Integer> e=map.get(key);
			if(e!=null){
				e.data=value;
				//move entry to the end.
				list.removeAndMoveToLast(e);
				/*list.remove(e);
				list.add(e);*/
			}
		}else{
			if(list.size>=capacity){
				while(list.size>=capacity){
					Entry<Integer, Integer> n=list.removeFirst();	
					if(n!=null)
						map.remove(n.key); //remove items from map by keys
				}
			}
			
			Entry<Integer, Integer> e=new Entry<Integer, Integer>(value);
			e.key=key;
			map.put(key, e);
			list.add(e);
		}
	}

	

	

	public static void main(String[] args) {
		LRUCache cache=new LRUCache(3);

		cache.set(1, 100);
		cache.set(2, 200);
		cache.set(3, 300);
		assertThat(cache.get(1), is(100));	
		cache.set(4, 400);		
		assertThat(cache.get(1), is(100));
		assertThat(cache.get(2), is(-1));
		cache.set(5, 500);	
		assertThat(cache.get(3), is(-1));
		assertThat(cache.get(4), is(400));
		cache.set(6, 600);	
		assertThat(cache.get(1), is(-1));


		cache=new LRUCache(2);
		cache.set(2,1);
		cache.set(3,2);
		assertTrue(cache.get(3)==2);
		assertTrue(cache.get(2)==1);
		cache.set(4,3);

		assertTrue(cache.get(2)==1);
		assertTrue(cache.get(3)==-1);
		assertTrue(cache.get(4)==3);

		cache=new LRUCache(105);			
		cache.set(33,219);cache.get(39);cache.set(96,56);cache.get(129);cache.get(115);cache.get(112);cache.set(3,280);cache.get(40);cache.set(85,193);cache.set(10,10);cache.set(100,136);cache.set(12,66);cache.set(81,261);cache.set(33,58);cache.get(3);cache.set(121,308);cache.set(129,263);cache.get(105);cache.set(104,38);cache.set(65,85);cache.set(3,141);cache.set(29,30);cache.set(80,191);cache.set(52,191);cache.set(8,300);cache.get(136);cache.set(48,261);cache.set(3,193);cache.set(133,193);cache.set(60,183);cache.set(128,148);cache.set(52,176);cache.get(48);cache.set(48,119);cache.set(10,241);cache.get(124);cache.set(130,127);cache.get(61);cache.set(124,27);cache.get(94);cache.set(29,304);cache.set(102,314);cache.get(110);cache.set(23,49);cache.set(134,12);cache.set(55,90);cache.get(14);cache.get(104);cache.set(77,165);cache.set(60,160);cache.get(117);cache.set(58,30);cache.get(54);cache.get(136);cache.get(128);cache.get(131);cache.set(48,114);cache.get(136);cache.set(46,51);cache.set(129,291);cache.set(96,207);cache.get(131);cache.set(89,153);cache.set(120,154);cache.get(111);cache.get(47);cache.get(5);cache.set(114,157);cache.set(57,82);cache.set(113,106);cache.set(74,208);cache.get(56);cache.get(59);cache.get(100);cache.get(132);cache.set(127,202);cache.get(75);cache.set(102,147);cache.get(37);cache.set(53,79);cache.set(119,220);cache.get(47);cache.get(101);cache.get(89);cache.get(20);cache.get(93);cache.get(7);cache.set(48,109);cache.set(71,146);cache.get(43);cache.get(122);cache.set(3,160);cache.get(17);cache.set(80,22);cache.set(80,272);cache.get(75);cache.get(117);cache.set(76,204);cache.set(74,141);cache.set(107,93);cache.set(34,280);cache.set(31,94);cache.get(132);cache.set(71,258);cache.get(61);cache.get(60);cache.set(69,272);cache.get(46);cache.set(42,264);cache.set(87,126);cache.set(107,236);cache.set(131,218);cache.get(79);cache.set(41,71);cache.set(94,111);cache.set(19,124);cache.set(52,70);cache.get(131);cache.get(103);cache.get(81);cache.get(126);cache.set(61,279);cache.set(37,100);cache.get(95);cache.get(54);cache.set(59,136);cache.set(101,219);cache.set(15,248);cache.set(37,91);cache.set(11,174);cache.set(99,65);cache.set(105,249);cache.get(85);cache.set(108,287);cache.set(96,4);cache.get(70);cache.get(24);cache.set(52,206);cache.set(59,306);cache.set(18,296);cache.set(79,95);cache.set(50,131);cache.set(3,161);cache.set(2,229);cache.set(39,183);cache.set(90,225);cache.set(75,23);cache.set(136,280);cache.get(119);cache.set(81,272);cache.get(106);cache.get(106);cache.get(70);cache.set(73,60);cache.set(19,250);cache.set(82,291);cache.set(117,53);cache.set(16,176);cache.get(40);cache.set(7,70);cache.set(135,212);cache.get(59);cache.set(81,201);cache.set(75,305);cache.get(101);cache.set(8,250);cache.get(38);cache.set(28,220);cache.get(21);cache.set(105,266);cache.get(105);cache.get(85);cache.get(55);cache.get(6);cache.set(78,83);cache.get(126);cache.get(102);cache.get(66);cache.set(61,42);cache.set(127,35);cache.set(117,105);cache.get(128);cache.get(102);cache.get(50);cache.set(24,133);cache.set(40,178);cache.set(78,157);cache.set(71,22);cache.get(25);cache.get(82);cache.get(129);cache.set(126,12);cache.get(45);cache.get(40);cache.get(86);cache.get(100);cache.set(30,110);cache.get(49);cache.set(47,185);cache.set(123,101);cache.get(102);cache.get(5);cache.set(40,267);cache.set(48,155);cache.get(108);cache.get(45);cache.set(14,182);cache.set(20,117);cache.set(43,124);cache.get(38);cache.set(77,158);cache.get(111);cache.get(39);cache.set(69,126);cache.set(113,199);cache.set(21,216);cache.get(11);cache.set(117,207);cache.get(30);cache.set(97,84);cache.get(109);cache.set(99,218);cache.get(109);cache.set(113,1);cache.get(62);cache.set(49,89);cache.set(53,311);cache.get(126);cache.set(32,153);cache.set(14,296);cache.get(22);cache.set(14,225);cache.get(49);cache.get(75);cache.set(61,241);cache.get(7);cache.get(6);cache.get(31);cache.set(75,15);cache.get(115);cache.set(84,181);cache.set(125,111);cache.set(105,94);cache.set(48,294);cache.get(106);cache.get(61);cache.set(53,190);cache.get(16);cache.set(12,252);cache.get(28);cache.set(111,122);cache.get(122);cache.set(10,21);cache.get(59);cache.get(72);cache.get(39);cache.get(6);cache.get(126);cache.set(131,177);cache.set(105,253);cache.get(26);cache.set(43,311);cache.get(79);cache.set(91,32);cache.set(7,141);cache.get(38);cache.get(13);cache.set(79,135);cache.get(43);cache.get(94);cache.set(80,182);cache.get(53);cache.set(120,309);cache.set(3,109);cache.get(97);cache.set(9,128);cache.set(114,121);cache.get(56);cache.get(56);cache.set(124,86);cache.set(34,145);cache.get(131);cache.get(78);cache.set(86,21);cache.get(98);cache.set(115,164);cache.set(47,225);cache.get(95);cache.set(89,55);cache.set(26,134);cache.set(8,15);cache.get(11);cache.set(84,276);cache.set(81,67);cache.get(46);cache.get(39);cache.get(92);cache.get(96);cache.set(89,51);cache.set(136,240);cache.get(45);cache.get(27);cache.set(24,209);cache.set(82,145);cache.get(10);cache.set(104,225);cache.set(120,203);cache.set(121,108);cache.set(11,47);cache.get(89);cache.set(80,66);cache.get(16);cache.set(95,101);cache.get(49);cache.get(1);cache.set(77,184);cache.get(27);cache.set(74,313);cache.set(14,118);cache.get(16);cache.get(74);cache.set(88,251);cache.get(124);cache.set(58,101);cache.set(42,81);cache.get(2);cache.set(133,101);cache.get(16);cache.set(1,254);cache.set(25,167);cache.set(53,56);cache.set(73,198);cache.get(48);cache.get(30);cache.get(95);cache.set(90,102);cache.set(92,56);cache.set(2,130);cache.set(52,11);cache.get(9);cache.get(23);cache.set(53,275);cache.set(23,258);cache.get(57);cache.set(136,183);cache.set(75,265);cache.get(85);cache.set(68,274);cache.set(15,255);cache.get(85);cache.set(33,314);cache.set(101,223);cache.set(39,248);cache.set(18,261);cache.set(37,160);cache.get(112);cache.get(65);cache.set(31,240);cache.set(40,295);cache.set(99,231);cache.get(123);cache.set(34,43);cache.get(87);cache.get(80);cache.set(47,279);cache.set(89,299);cache.get(72);cache.set(26,277);cache.set(92,13);cache.set(46,92);cache.set(67,163);cache.set(85,184);cache.get(38);cache.set(35,65);cache.get(70);cache.get(81);cache.set(40,65);cache.get(80);cache.set(80,23);cache.set(76,258);cache.get(69);cache.get(133);cache.set(123,196);cache.set(119,212);cache.set(13,150);cache.set(22,52);cache.set(20,105);cache.set(61,233);cache.get(97);cache.set(128,307);cache.get(85);cache.get(80);cache.get(73);cache.get(30);cache.set(46,44);cache.get(95);cache.set(121,211);cache.set(48,307);cache.get(2);cache.set(27,166);cache.get(50);cache.set(75,41);cache.set(101,105);cache.get(2);cache.set(110,121);cache.set(32,88);cache.set(75,84);cache.set(30,165);cache.set(41,142);cache.set(128,102);cache.set(105,90);cache.set(86,68);cache.set(13,292);cache.set(83,63);cache.set(5,239);cache.get(5);cache.set(68,204);cache.get(127);cache.set(42,137);cache.get(93);cache.set(90,258);cache.set(40,275);cache.set(7,96);cache.get(108);cache.set(104,91);cache.get(63);cache.get(31);cache.set(31,89);cache.get(74);cache.get(81);cache.set(126,148);cache.get(107);cache.set(13,28);cache.set(21,139);cache.get(114);cache.get(5);cache.get(89);cache.get(133);cache.get(20);cache.set(96,135);cache.set(86,100);cache.set(83,75);cache.get(14);cache.set(26,195);cache.get(37);cache.set(1,287);cache.get(79);cache.get(15);cache.get(6);cache.set(68,11);cache.get(52);cache.set(124,80);cache.set(123,277);cache.set(99,281);cache.get(133);cache.get(90);cache.get(45);cache.get(127);cache.set(9,68);cache.set(123,6);cache.set(124,251);cache.set(130,191);cache.set(23,174);cache.set(69,295);cache.get(32);cache.get(37);cache.set(1,64);cache.set(48,116);cache.get(68);cache.set(117,173);cache.set(16,89);cache.get(84);cache.set(28,234);cache.get(129);cache.get(89);cache.get(55);cache.get(83);cache.set(99,264);cache.get(129);cache.get(84);cache.get(14);cache.set(26,274);cache.get(109);cache.get(110);cache.set(96,120);cache.set(128,207);cache.get(12);cache.set(99,233);cache.set(20,305);cache.set(26,24);cache.set(102,32);cache.get(82);cache.set(16,30);cache.set(5,244);cache.get(130);cache.set(109,36);cache.set(134,162);cache.set(13,165);cache.set(45,235);cache.set(112,80);cache.get(6);cache.set(34,98);cache.set(64,250);cache.set(18,237);cache.set(72,21);cache.set(42,105);cache.set(57,108);cache.set(28,229);cache.get(83);cache.set(1,34);cache.set(93,151);cache.set(132,94);cache.set(18,24);cache.set(57,68);cache.set(42,137);cache.get(35);cache.get(80);cache.set(10,288);cache.get(21);cache.get(115);cache.get(131);cache.get(30);cache.get(43);cache.set(97,262);cache.set(55,146);cache.set(81,112);cache.set(2,212);cache.set(5,312);cache.set(82,107);cache.set(14,151);cache.get(77);cache.set(60,42);cache.set(90,309);cache.get(90);cache.set(131,220);cache.get(86);cache.set(106,85);cache.set(85,254);cache.get(14);cache.set(66,262);cache.set(88,243);cache.get(3);cache.set(50,301);cache.set(118,91);cache.get(25);cache.get(105);cache.get(100);cache.get(89);cache.set(111,152);cache.set(65,24);cache.set(41,264);cache.get(117);cache.get(117);cache.set(80,45);cache.get(38);cache.set(11,151);cache.set(126,203);cache.set(128,59);cache.set(6,129);cache.get(91);cache.set(118,2);cache.set(50,164);cache.get(74);cache.get(80);cache.set(48,308);cache.set(109,82);cache.set(3,48);cache.set(123,10);cache.set(59,249);cache.set(128,64);cache.set(41,287);cache.set(52,278);cache.set(98,151);cache.get(12);cache.get(25);cache.set(18,254);cache.set(24,40);cache.get(119);cache.set(66,44);cache.set(61,19);cache.set(80,132);cache.set(62,111);cache.get(80);cache.set(57,188);cache.get(132);cache.get(42);cache.set(18,314);cache.get(48);cache.set(86,138);cache.get(8);cache.set(27,88);cache.set(96,178);cache.set(17,104);cache.set(112,86);cache.get(25);cache.set(129,119);cache.set(93,44);cache.get(115);cache.set(33,36);cache.set(85,190);cache.get(10);cache.set(52,182);cache.set(76,182);cache.get(109);cache.get(118);cache.set(82,301);cache.set(26,158);cache.get(71);cache.set(108,309);cache.set(58,132);cache.set(13,299);cache.set(117,183);cache.get(115);cache.get(89);cache.get(42);cache.set(11,285);cache.set(30,144);cache.get(69);cache.set(31,53);cache.get(21);cache.set(96,162);cache.set(4,227);cache.set(77,120);cache.set(128,136);cache.get(92);cache.set(119,208);cache.set(87,61);cache.set(9,40);cache.set(48,273);cache.get(95);cache.get(35);cache.set(62,267);cache.set(88,161);cache.get(59);cache.get(85);cache.set(131,53);cache.set(114,98);cache.set(90,257);cache.set(108,46);cache.get(54);cache.set(128,223);cache.set(114,168);cache.set(89,203);cache.get(100);cache.get(116);cache.get(14);cache.set(61,104);cache.set(44,161);cache.set(60,132);cache.set(21,310);cache.get(89);cache.set(109,237);cache.get(105);cache.get(32);cache.set(78,101);cache.set(14,71);cache.set(100,47);cache.set(102,33);cache.set(44,29);cache.get(85);cache.get(37);cache.set(68,175);cache.set(116,182);cache.set(42,47);cache.get(9);cache.set(64,37);cache.set(23,32);cache.set(11,124);cache.set(130,189);cache.get(65);cache.set(33,219);cache.set(79,253);cache.get(80);cache.get(16);cache.set(38,18);cache.set(35,67);cache.get(107);cache.get(88);cache.set(37,13);cache.set(71,188);cache.get(35);cache.set(58,268);cache.set(18,260);cache.set(73,23);cache.set(28,102);cache.get(129);cache.get(88);cache.get(65);cache.get(80);cache.set(119,146);cache.get(113);cache.get(62);cache.set(123,138);cache.set(18,1);cache.set(26,208);cache.get(107);cache.get(107);cache.set(76,132);cache.set(121,191);cache.get(4);cache.get(8);cache.get(117);cache.set(11,118);cache.get(43);cache.get(69);cache.get(136);cache.set(66,298);cache.get(25);cache.get(71);cache.get(100);cache.set(26,141);cache.set(53,256);cache.set(111,205);cache.set(126,106);cache.get(43);cache.set(14,39);cache.set(44,41);cache.set(23,230);cache.get(131);cache.get(53);cache.set(104,268);cache.get(30);cache.set(108,48);cache.set(72,45);cache.get(58);cache.get(46);cache.set(128,301);cache.get(71);cache.get(99);cache.get(113);cache.get(121);cache.set(130,122);cache.set(102,5);cache.set(111,51);cache.set(85,229);cache.set(86,157);cache.set(82,283);cache.set(88,52);cache.set(136,105);cache.get(40);cache.get(63);cache.set(114,244);cache.set(29,82);cache.set(83,278);cache.get(131);cache.set(56,33);cache.get(123);cache.get(11);cache.get(119);cache.set(119,1);cache.set(48,52);cache.get(47);cache.set(127,136);cache.set(78,38);cache.set(117,64);cache.set(130,134);cache.set(93,69);cache.set(70,98);cache.get(68);cache.set(4,3);cache.set(92,173);cache.set(114,65);cache.set(7,309);cache.get(31);cache.set(107,271);cache.set(110,69);cache.get(45);cache.set(35,288);cache.get(20);cache.set(38,79);cache.get(46);cache.set(6,123);cache.get(19);cache.set(84,95);cache.get(76);cache.set(71,31);cache.set(72,171);cache.set(35,123);cache.get(32);cache.set(73,85);cache.get(94);cache.get(128);cache.get(28);cache.get(38);cache.get(109);cache.set(85,197);cache.set(10,41);cache.set(71,50);cache.get(128);cache.set(3,55);cache.set(15,9);cache.set(127,215);cache.get(17);cache.get(37);cache.set(111,272);cache.set(79,169);cache.set(86,206);cache.set(40,264);cache.get(134);cache.set(16,207);cache.set(27,127);cache.set(29,48);cache.set(32,122);cache.set(15,35);cache.set(117,36);cache.get(127);cache.get(36);cache.set(72,70);cache.set(49,201);cache.set(89,215);cache.set(134,290);cache.set(77,64);cache.set(26,101);cache.get(99);cache.set(36,96);cache.set(84,129);cache.set(125,264);cache.get(43);cache.get(38);cache.set(24,76);cache.set(45,2);cache.set(32,24);cache.set(84,235);cache.set(16,240);cache.set(17,289);cache.set(49,94);cache.set(90,54);cache.set(88,199);cache.get(23);cache.set(87,19);cache.set(11,19);cache.get(24);cache.get(57);cache.get(4);cache.get(40);cache.set(133,286);cache.set(127,231);cache.get(51);cache.set(52,196);cache.get(27);cache.get(10);cache.get(93);cache.set(115,143);cache.set(62,64);cache.set(59,200);cache.set(75,85);cache.set(7,93);cache.set(117,270);cache.set(116,6);cache.get(32);cache.get(135);cache.set(2,140);cache.set(23,1);cache.set(11,69);cache.set(89,30);cache.set(27,14);cache.get(100);cache.get(61);cache.set(99,41);cache.set(88,12);cache.get(41);cache.set(52,203);cache.get(65);cache.set(62,78);cache.set(104,276);cache.set(105,307);cache.get(7);cache.set(23,123);cache.get(22);cache.set(35,299);cache.get(69);cache.get(11);cache.set(14,112);cache.get(115);cache.get(112);cache.get(108);cache.set(110,165);cache.set(83,165);cache.set(36,260);cache.set(54,73);cache.get(36);cache.set(93,69);cache.get(134);cache.set(125,96);cache.set(74,127);cache.set(110,305);cache.set(92,309);cache.set(87,45);cache.set(31,266);cache.get(10);cache.set(114,206);cache.set(49,141);cache.get(82);cache.set(92,3);cache.set(91,160);cache.get(41);cache.set(60,147);cache.set(36,239);cache.set(23,296);cache.set(134,120);cache.get(6);cache.set(5,283);cache.set(117,68);cache.get(35);cache.get(120);cache.set(44,191);cache.set(121,14);cache.set(118,113);cache.set(84,106);cache.get(23);cache.set(15,240);cache.get(37);cache.set(52,256);cache.set(119,116);cache.set(101,7);cache.set(14,157);cache.set(29,225);cache.set(4,247);cache.set(8,112);cache.set(8,189);cache.set(96,220);cache.get(104);cache.set(72,106);cache.set(23,170);cache.set(67,209);cache.set(70,39);cache.get(18);cache.get(6);cache.get(34);cache.set(121,157);cache.get(16);cache.get(19);cache.set(83,283);cache.set(13,22);cache.set(33,143);cache.set(88,133);cache.get(88);cache.set(5,49);cache.get(38);cache.get(110);cache.get(67);cache.set(23,227);cache.get(68);cache.get(3);cache.set(27,265);cache.get(31);cache.set(13,103);cache.get(116);cache.set(111,282);cache.set(43,71);cache.get(134);cache.set(70,141);cache.get(14);cache.get(119);cache.get(43);cache.get(122);cache.set(38,187);cache.set(8,9);cache.get(63);cache.set(42,140);cache.get(83);cache.get(92);cache.get(106);cache.get(28);cache.set(57,139);cache.set(36,257);cache.set(30,204);cache.get(72);cache.set(105,243);cache.get(16);cache.set(74,25);cache.get(22);cache.set(118,144);cache.get(133);cache.get(71);cache.set(99,21);cache.get(26);cache.get(35);cache.set(89,209);cache.set(106,158);cache.set(76,63);cache.set(112,216);cache.get(128);cache.get(54);cache.set(16,165);cache.set(76,206);cache.set(69,253);cache.get(23);cache.set(54,111);cache.get(80);cache.set(111,72);cache.set(95,217);cache.get(118);cache.set(4,146);cache.get(47);cache.set(108,290);cache.get(43);cache.set(70,8);cache.get(117);cache.get(121);cache.set(42,220);cache.get(48);cache.get(32);cache.set(68,213);cache.set(30,157);cache.set(62,68);cache.get(58);cache.set(125,283);cache.set(132,45);cache.get(85);cache.get(92);cache.set(23,257);cache.get(74);cache.set(18,256);cache.get(90);cache.set(10,158);cache.set(57,34);cache.get(27);cache.get(107);
		
		ArrayList<Integer> result=new ArrayList<Integer>();
		cache=new LRUCache(10);		
		cache.set(10,13);cache.set(3,17);cache.set(6,11);cache.set(10,5);cache.set(9,10);result.add(cache.get(13));
		cache.set(2,19);result.add(cache.get(2));
		result.add(cache.get(3));cache.set(5,25);result.add(cache.get(8));cache.set(9,22);cache.set(5,5);cache.set(1,30);result.add(cache.get(11));
		cache.set(9,12);result.add(cache.get(7));result.add(cache.get(5));result.add(cache.get(8));result.add(cache.get(9));cache.set(4,30);cache.set(9,3);
		result.add(cache.get(9));result.add(cache.get(10));result.add(cache.get(10));cache.set(6,14);cache.set(3,1);result.add(cache.get(3));cache.set(10,11);
		result.add(cache.get(8));cache.set(2,14);result.add(cache.get(1));result.add(cache.get(5));result.add(cache.get(4));cache.set(11,4);cache.set(12,24);
		cache.set(5,18);result.add(cache.get(13));cache.set(7,23);result.add(cache.get(8));result.add(cache.get(12));cache.set(3,27);cache.set(2,12);
		result.add(cache.get(5));cache.set(2,9);cache.set(13,4);cache.set(8,18);cache.set(1,7);result.add(cache.get(6));cache.set(9,29);cache.set(8,21);
		result.add(cache.get(5));cache.set(6,30);cache.set(1,12);result.add(cache.get(10));cache.set(4,15);cache.set(7,22);cache.set(11,26);cache.set(8,17);
		cache.set(9,29);result.add(cache.get(5));cache.set(3,4);cache.set(11,30);result.add(cache.get(12));cache.set(4,29);result.add(cache.get(3));result.add(cache.get(9));
		result.add(cache.get(6));cache.set(3,4);result.add(cache.get(1));result.add(cache.get(10));cache.set(3,29);cache.set(10,28);cache.set(1,20);cache.set(11,13);
		result.add(cache.get(3));cache.set(3,12);cache.set(3,8);cache.set(10,9);cache.set(3,26);result.add(cache.get(8));result.add(cache.get(7));result.add(cache.get(5));
		cache.set(13,17);cache.set(2,27);cache.set(11,15);result.add(cache.get(12));cache.set(9,19);cache.set(2,15);cache.set(3,16);result.add(cache.get(1));
		cache.set(12,17);cache.set(9,1);cache.set(6,19);result.add(cache.get(4));result.add(cache.get(5));result.add(cache.get(5));cache.set(8,1);cache.set(11,7);
		cache.set(5,2);cache.set(9,28);result.add(cache.get(1));cache.set(2,2);cache.set(7,4);cache.set(4,22);cache.set(7,24);cache.set(9,26);cache.set(13,28);
		cache.set(11,26);
		
		assertThat(result, is(Arrays.asList(-1,19,17,-1,-1,-1,5,-1,12,3,5,5,1,-1,30,5,30,-1,-1,24,18,-1,18,-1,18,-1,4,29,30,12,-1,29,17,22,18,-1,20,-1,18,18,20)));
		System.out.println("all test cases passed");
	}

}
