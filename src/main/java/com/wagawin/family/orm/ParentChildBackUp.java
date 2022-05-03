package com.wagawin.family.orm;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(indexes = { @Index(name = "parentChild_index", columnList = "childId,parentId") })
public class ParentChildBackUp implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long childId;
	private Long parentId;

	public ParentChildBackUp(Long parentId, Long childId){
		this.parentId = parentId;
		this.childId = childId;
	}

}
