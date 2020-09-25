package com.xhw.springbootnutz;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.xhw.springbootnutz.model.dto.SeqId;
import com.xhw.springbootnutz.model.dto.StudentInfo;
import com.xhw.springbootnutz.model.dto.StudentInfoDto;
import com.xhw.springbootnutz.model.mapped.PrimaryStudent;
import com.xhw.springbootnutz.model.mapped.StudentInfoDtoTemp;
import com.xhw.springbootnutz.util.QRCodeUtils;
import com.xhw.springbootnutz.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootNutzApplicationTests {


	@Autowired
	Dao dao;

	/*解决印江中学学生出入类型数据问题*/
	@Test
	public void testStuType(){
		File file=new File("C:\\Users\\Shinelon\\Documents\\印江补录学生数据\\印江中心（高一年级）.xlsx");
		ImportParams params = new ImportParams();
		params.setStartRows(0);
		List<StudentInfo> list = ExcelImportUtil.importExcel(file, StudentInfo.class, params);
		for (StudentInfo studentInfo : list) {

			/*int count=dao.count(PrimaryStudent.class, Cnd.where("'JCXS010101'", "=", studentInfo.getStuNo()));
			if(count>1){
				System.out.println("查询到学号重复："+studentInfo.getStuNo()+";重复次数："+count);
			}else{
				System.out.println("1");
			}*/


			PrimaryStudent primaryStudent = dao.fetch(PrimaryStudent.class, Cnd.where("\"JCXS010101\"", "=", studentInfo.getStuNo()));
			if(primaryStudent!=null){
				primaryStudent.setPassType(studentInfo.getStuType());
				primaryStudent.setGenderCode(studentInfo.getStuSex());
				int i = dao.update(primaryStudent);
				if(i>0){
					System.out.println("修改出入类型成功");
				}else{
					System.out.println("修改出入类型失败....."+studentInfo.toString());
				}
			}else{
				System.out.println("未查询到学号："+studentInfo.getStuNo()+"对呀的学生信息");
			}
		}

	}

	/**
	 * 生成学生的二维码信息
	 */
	@Test
	public void QRCode() throws Exception {
		Sql sql = Sqls.create("select bps.id unionId,bps.\"JCXS010101\" studentId,bps.\"JCTB020101\" stuName from bl_primary_student bps \n" +
				"\tleft join bl_student_class_relation bscr on bps.id=bscr.student_id\n" +
				"\tleft join bl_school_class_relation bscr2 on bscr2.class_id=bscr.class_id\n" +
				"\twhere bscr2.school_id =@schoolId");
		sql.params().set("schoolId","688906280118521856");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(StudentInfoDto.class));
		dao.execute(sql);
		List<StudentInfoDto> list = sql.getList(StudentInfoDto.class);
		int i=0;
		for (StudentInfoDto studentInfoDto : list) {

			String unionId=studentInfoDto.getUnionId();
			String salt="d380cce937f043bdb5c72f0ca072c773";
			String unionId_salt=StringUtils.encrypByMd5(unionId+"_"+salt);
			String url="https://m.gzeducard.net/userinfo?type=1&unionId="+unionId+"&md5="+unionId_salt;
			String stuName=studentInfoDto.getStudentId()+"_"+studentInfoDto.getStuName();
			QRCodeUtils.encode(url, null, "C:\\Users\\Shinelon\\Pictures\\student\\紫光中学", true,stuName);
			i++;
		}
		System.out.println("一共生成："+i+"张二维码；");
	}

	/**
	 * 生成学生的二维码信息
	 */
	@Test
	public void QRCode2() throws Exception {
		List<StudentInfoDtoTemp> studentInfoDtoTemps = dao.query(StudentInfoDtoTemp.class, Cnd.NEW());
		int i=0;
		for (StudentInfoDtoTemp studentInfoDto : studentInfoDtoTemps) {

			String unionId=studentInfoDto.getUnionId();
			String salt="d380cce937f043bdb5c72f0ca072c773";
			String unionId_salt=StringUtils.encrypByMd5(unionId+"_"+salt);
			String url="https://m.gzeducard.net/userinfo?type=1&unionId="+unionId+"&md5="+unionId_salt;
			String stuName=studentInfoDto.getStudentId()+"_"+studentInfoDto.getStuName();
			QRCodeUtils.encode(url, null, "C:\\Users\\Shinelon\\Pictures\\student\\普安县雪浦中学初一年级制卡数据143人", true,stuName);
			i++;
		}
		System.out.println("一共生成："+i+"张二维码；");
	}

	/**
	 * 生成单个学生的二维码信息
	 */
	@Test
	public void QRCodeOne() throws Exception {
		String unionId="5223231090010002559";
		String salt="d380cce937f043bdb5c72f0ca072c773";
		String unionId_salt=StringUtils.encrypByMd5(unionId+"_"+salt);
		String url="https://m.gzeducard.net/userinfo?type=1&unionId="+unionId+"&md5="+unionId_salt;
		String stuName="qs30047_王忠海";
		QRCodeUtils.encode(url, null, "C:\\Users\\Shinelon\\Pictures\\student\\", true,stuName);
	}


	@Test
	public void tes(){
		Sql sql_id = Sqls.create("select max(id) from $table");
		sql_id.vars().set("table","bl_education_bureau_account");
		sql_id.setCallback(Sqls.callback.integer());
		try {
			dao.execute(sql_id);
			int sqlIdInt = sql_id.getInt();
			System.out.println("当前表bl_education_bureau_account的id最大值："+sqlIdInt+"\n");
		}catch (Exception e){
			System.out.println("错误信息："+e.getMessage());
		}
		System.out.println("+++++++++++++++++++++++++");


	}

	@Test
	public void contextLoads() {
		Sql sql = Sqls.create("SELECT c.relname FROM pg_class c WHERE  c.relname like '%_seq'");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(SeqId.class));
		dao.execute(sql);
		List<SeqId> list = sql.getList(SeqId.class);
		int i=0;
		for (SeqId  seqId: list) {
			i++;
			String relname = seqId.getRelname();//dm_table_column_id_seq    //take_seq
			System.out.println("当前序列名称："+relname);
			int lastIndexOf = relname.lastIndexOf("_id_seq")==-1?relname.lastIndexOf("_seq"):relname.lastIndexOf("_id_seq");

			//获取当前表的id最大值
			String substring = relname.substring(0, lastIndexOf);//表名
			System.out.println("当前表名称："+substring);
			Sql sql_id = Sqls.create("select max(id) from $table");
			sql_id.vars().set("table",substring);
			sql_id.setCallback(Sqls.callback.integer());
			int sqlIdInt=0;
			try {
				dao.execute(sql_id);
				sqlIdInt = sql_id.getInt();
				System.out.println("当前表"+substring+"的id最大值："+sqlIdInt+"\n");

				//修改序列值
				Sql sql_update_seq_id = Sqls.create("select setval('$seqName',$seqValue)");
				sql_update_seq_id.vars().set("seqName",relname).set("seqValue",sqlIdInt==0?1:sqlIdInt);
				sql_update_seq_id.setCallback(Sqls.callback.integer());
				dao.execute(sql_update_seq_id);
			}catch (Exception e){
				System.out.println("错误信息："+e.getMessage());
			}
		}
		System.out.println("一共修改了"+i+"个序列的值");
	}

}
