package simulator.crystal.slot.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtUtil {

	private final String secretKey = System.getenv("JWT_SECRET_KEY");


	public String generateToken (String userName) {
		return Jwts.builder()
				.subject(userName)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(getSigningKey())
				.compact();
	}

	public boolean validateToken(String token, String userName) {
		try {
			Claims claims = Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
			return claims.getSubject().equals(userName);
		} catch (Exception e) {
			return false;
		}
	}

	public String extractUsername(String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}

	private SecretKey getSigningKey () {
		if(secretKey == null) {
			throw new RuntimeException("JWT_SECRET_KEY not found");
		}
		return new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
	}
}
