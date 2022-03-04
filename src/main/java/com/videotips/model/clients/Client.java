package com.videotips.model.clients;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {
	private Long id;
	private String name;
	private String lastName;
	private Long phoneNumber;
	private Address address;
}
@Data
class Address {
	private String street;
	private Long  number;
}
