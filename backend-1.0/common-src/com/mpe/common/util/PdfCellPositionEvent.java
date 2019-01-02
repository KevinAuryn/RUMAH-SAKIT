/**
 * 
 */
package com.mpe.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class PdfCellPositionEvent implements PdfPCellEvent {
	
	float topCell, bottomCell;
	Log log = LogFactory.getFactory().getInstance(this.getClass());

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfPCellEvent#cellLayout(com.itextpdf.text.pdf.PdfPCell, com.itextpdf.text.Rectangle, com.itextpdf.text.pdf.PdfContentByte[])
	 */
	@Override
	public void cellLayout(PdfPCell cell, Rectangle rec, PdfContentByte[] bytes) {
		this.topCell = rec.getTop();
		this.bottomCell = rec.getBottom();
		//log.info("L : "+rec.getLeft());
		//log.info("R : "+rec.getRight());
		//log.info("T : "+rec.getTop());
		//log.info("B : "+rec.getBottom());
	}

	public float getTopCell() {
		return topCell;
	}

	public float getBottomCell() {
		return bottomCell;
	}
	
	

}
