package com.nguyenvosongtoan.r2sshop.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtils {
    public static final String SECRET = "54455357342478364763268483726487236482638746283764832";

    // Trích xuất tên người dùng từ JWT
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Trích xuất thời gian hết hạn từ JWT
    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Xác thực JWT bằng cách so sánh tên người dùng và kiểm tra thời gian hết hạn
    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Tạo JWT cho người dùng đã xác thực thành công
    public static String generateToken(String userName){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userName);
    }

    // Tạo JWT từ các thông tin và ký bằng secret key
    private static String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    // Lấy secret key từ chuỗi bí mật và trả về đối tượng Key
    private static Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Trích xuất thông tin từ JWT bằng cách sử dụng một Resolver (hàm giải quyết)
    private static  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Trích xuất toàn bộ thông tin từ JWT và trả về đối tượng Claims
    private static Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Kiểm tra xem JWT đã hết hạn hay chưa
    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
