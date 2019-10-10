
/* getCheckDatailsCount.get */
SELECT student_id stuId,stu_name stuName,student_no stuNo,
sum(case when state = 1 then 1 else 0 end ) normal,
sum(case when state = 2 then 1 else 0 end ) late,
sum(case when state = 3 then 1 else 0 end ) leaveEarly,
sum(case when state = 4 then 1 else 0 end ) truancy,
sum(case when state = 5 then 1 else 0 end ) exception,
sum(case when state = 6 then 1 else 0 end ) leave
FROM checking_details $condition
group by student_id,stu_name,student_no
limit @pageSize offset @pageNumber;
/* getCheckDatailsCount.totalSize */
select count(total.*) totalNumber from(SELECT 1 FROM checking_details $condition group by student_id,stu_name,student_no)total;
