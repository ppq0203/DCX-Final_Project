package project.momento.chart.mapper;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.momento.chart.dto.ChartDto;
import project.momento.page.Criteria;


@Mapper
public interface ChartMapper {

	void userpage(ChartDto chartDto);

	List<ChartDto> getProductList(Criteria cri);

	int getProductListCount(Criteria cri);

	int insertResult(ChartDto chartDto);

	void insertResultDetail(@Param("resultSeq") int resultSeq, @Param("prodSeq") String prodSeq,@Param("userSeq") int userSeq);

	int getProductDetailListCount(Criteria cri);

	List<ChartDto> getProductDetailList(Criteria cri);

	List<ChartDto> getResultList(Criteria cri);

}