/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nmt.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author Tri Nguyen Minh
 */
@FacesValidator("UploadValidator")
public class UploadValidator implements Validator{
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException{
        Part p = (Part) value;
        //Rang buoc anh loai png,jpg
        if(!p.getContentType().equals("image/png") && 
           !p.getContentType().equals("image/jpg")){
            FacesMessage msg= new FacesMessage("Need png/jpg");
            throw new ValidatorException(msg);
        }
        //Dung luong <=2MB
        if(p.getSize() > 2097152){
            FacesMessage msg= new FacesMessage("Size <= 2MB");
            throw new ValidatorException(msg);
        }
    }
}
