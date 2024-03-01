package com.example.studybuddybackend.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Willow
 **/
@Data
public class DeleteRequest implements Serializable {

    private static final long serialVersionUID = 871703787582679004L;

    /**
     * 队伍id
     */
    private Long id;
}
