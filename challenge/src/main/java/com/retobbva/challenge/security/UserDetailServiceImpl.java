package com.retobbva.challenge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.retobbva.challenge.jpa.UserRepository;
import com.retobbva.challenge.model.UserModel;

import rx.Observable;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userJPA;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final UserModel userModel=new UserModel();
		
		
		Observable<UserModel> obs = Observable.from(userJPA.findAll());
		obs.filter(u->u.getEmail().equals(email)).subscribe(u->{
		userModel.setId(u.getId());
		userModel.setEmail(email);
		userModel.setName(u.getName());
		userModel.setPassword(u.getPassword());
		});
		
		return new UserDetailsImpl(userModel);
	}
	
	
	

}
