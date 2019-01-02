/**
 * 
 */
package com.mpe.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;


/**
 * @author Agung Hadiwaluyo
 *
 */
public class PdfTableHeightEvent implements PdfPTableEvent {
	
	float topHeight, bottomHeight;
	Log log = LogFactory.getFactory().getInstance(this.getClass());

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPTableEvent#tableLayout(com.itextpdf.text.pdf.PdfPTable, float[][], float[], int, int, com.itextpdf.text.pdf.PdfContentByte[])
	 */
	@Override
	public void tableLayout(PdfPTable table, float[][] width, float[] height,
			int headerRows, int rowStart, PdfContentByte[] arg5) {
		this.topHeight = height[0];
		//log.info("Table height0 : "+height[0]);
		//log.info("table height1 : "+height[1]);
	}

	public float getTopHeight() {
		return topHeight;
	}

	public void setTopHeight(float topHeight) {
		this.topHeight = topHeight;
	}

	public float getBottomHeight() {
		return bottomHeight;
	}

	public void setBottomHeight(float bottomHeight) {
		this.bottomHeight = bottomHeight;
	}
	
	
	
	

}
