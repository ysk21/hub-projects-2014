package cn.kgc.tangcco.kgbd1020.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRang {
	private int pageNumber;
	private int pageSize;
	private int pageIndex = (pageNumber - 1) * pageSize;

	public PageRang(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

}
