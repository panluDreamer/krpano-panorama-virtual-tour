var krpanoplugin = function() {
	var a = this;
	var f = null;
	var c = null;
	var g = null;
	var e = null;
	a.registerplugin = function(i, h, j) {
		f = i;// f=krpano
		c = j;// c=plugin
		c.get_qr_image = b
	};
	a.unloadplugin = function() {
		c = null;
		f = null
	};
	var d = function() {
		var v = function(J, z) {
			var R = 236;
			var Q = 17;
			var D = J;
			var F = p[z];
			var B = null;
			var M = 0;
			var x = null;
			var H = new Array();
			var N = {};
			var K = function(T, S) {
				M = D * 4 + 17;
				B = function(X) {
					var V = new Array(X);
					for ( var W = 0; W < X; W += 1) {
						V[W] = new Array(X);
						for ( var U = 0; U < X; U += 1) {
							V[W][U] = null
						}
					}
					return V
				}(M);
				G(0, 0);
				G(M - 7, 0);
				G(0, M - 7);
				I();
				y();
				P(T, S);
				if (D >= 7) {
					E(T)
				}
				if (x == null) {
					x = A(D, F, H)
				}
				L(x, S)
			};
			var G = function(U, S) {
				for ( var T = -1; T <= 7; T += 1) {
					if (U + T <= -1 || M <= U + T) {
						continue
					}
					for ( var V = -1; V <= 7; V += 1) {
						if (S + V <= -1 || M <= S + V) {
							continue
						}
						if ((0 <= T && T <= 6 && (V == 0 || V == 6))
								|| (0 <= V && V <= 6 && (T == 0 || T == 6))
								|| (2 <= T && T <= 4 && 2 <= V && V <= 4)) {
							B[U + T][S + V] = true
						} else {
							B[U + T][S + V] = false
						}
					}
				}
			};
			var C = function() {
				var V = 0;
				var U = 0;
				for ( var T = 0; T < 8; T += 1) {
					K(true, T);
					var S = o.getLostPoint(N);
					if (T == 0 || V > S) {
						V = S;
						U = T
					}
				}
				return U
			};
			var y = function() {
				for ( var S = 8; S < M - 8; S += 1) {
					if (B[S][6] != null) {
						continue
					}
					B[S][6] = (S % 2 == 0)
				}
				for ( var T = 8; T < M - 8; T += 1) {
					if (B[6][T] != null) {
						continue
					}
					B[6][T] = (T % 2 == 0)
				}
			};
			var I = function() {
				var Y = o.getPatternPosition(D);
				for ( var U = 0; U < Y.length; U += 1) {
					for ( var T = 0; T < Y.length; T += 1) {
						var W = Y[U];
						var S = Y[T];
						if (B[W][S] != null) {
							continue
						}
						for ( var V = -2; V <= 2; V += 1) {
							for ( var X = -2; X <= 2; X += 1) {
								if (V == -2 || V == 2 || X == -2 || X == 2
										|| (V == 0 && X == 0)) {
									B[W + V][S + X] = true
								} else {
									B[W + V][S + X] = false
								}
							}
						}
					}
				}
			};
			var E = function(V) {
				var U = o.getBCHTypeNumber(D);
				for ( var T = 0; T < 18; T += 1) {
					var S = (!V && ((U >> T) & 1) == 1);
					B[Math.floor(T / 3)][T % 3 + M - 8 - 3] = S
				}
				for ( var T = 0; T < 18; T += 1) {
					var S = (!V && ((U >> T) & 1) == 1);
					B[T % 3 + M - 8 - 3][Math.floor(T / 3)] = S
				}
			};
			var P = function(X, W) {
				var V = (F << 3) | W;
				var U = o.getBCHTypeInfo(V);
				for ( var T = 0; T < 15; T += 1) {
					var S = (!X && ((U >> T) & 1) == 1);
					if (T < 6) {
						B[T][8] = S
					} else {
						if (T < 8) {
							B[T + 1][8] = S
						} else {
							B[M - 15 + T][8] = S
						}
					}
				}
				for ( var T = 0; T < 15; T += 1) {
					var S = (!X && ((U >> T) & 1) == 1);
					if (T < 8) {
						B[8][M - T - 1] = S
					} else {
						if (T < 9) {
							B[8][15 - T - 1 + 1] = S
						} else {
							B[8][15 - T - 1] = S
						}
					}
				}
				B[M - 8][8] = (!X)
			};
			var L = function(X, T) {
				var V = -1;
				var ac = M - 1;
				var W = 7;
				var S = 0;
				var aa = o.getMaskFunction(T);
				for ( var U = M - 1; U > 0; U -= 2) {
					if (U == 6) {
						U -= 1
					}
					while (true) {
						for ( var Z = 0; Z < 2; Z += 1) {
							if (B[ac][U - Z] == null) {
								var Y = false;
								if (S < X.length) {
									Y = (((X[S] >>> W) & 1) == 1)
								}
								var ab = aa(ac, U - Z);
								if (ab) {
									Y = !Y
								}
								B[ac][U - Z] = Y;
								W -= 1;
								if (W == -1) {
									S += 1;
									W = 7
								}
							}
						}
						ac += V;
						if (ac < 0 || M <= ac) {
							ac -= V;
							V = -V;
							break
						}
					}
				}
			};
			var O = function(ac, af) {
				var U = 0;
				var ai = 0;
				var ag = 0;
				var T = new Array(af.length);
				var X = new Array(af.length);
				for ( var aa = 0; aa < af.length; aa += 1) {
					var ab = af[aa].dataCount;
					var S = af[aa].totalCount - ab;
					ai = Math.max(ai, ab);
					ag = Math.max(ag, S);
					T[aa] = new Array(ab);
					for ( var ad = 0; ad < T[aa].length; ad += 1) {
						T[aa][ad] = 255 & ac.getBuffer()[ad + U]
					}
					U += ab;
					var Y = o.getErrorCorrectPolynomial(S);
					var ah = l(T[aa], Y.getLength() - 1);
					var V = ah.mod(Y);
					X[aa] = new Array(Y.getLength() - 1);
					for ( var ad = 0; ad < X[aa].length; ad += 1) {
						var Z = ad + V.getLength() - X[aa].length;
						X[aa][ad] = (Z >= 0) ? V.getAt(Z) : 0
					}
				}
				var ae = 0;
				for ( var ad = 0; ad < af.length; ad += 1) {
					ae += af[ad].totalCount
				}
				var aj = new Array(ae);
				var W = 0;
				for ( var ad = 0; ad < ai; ad += 1) {
					for ( var aa = 0; aa < af.length; aa += 1) {
						if (ad < T[aa].length) {
							aj[W] = T[aa][ad];
							W += 1
						}
					}
				}
				for ( var ad = 0; ad < ag; ad += 1) {
					for ( var aa = 0; aa < af.length; aa += 1) {
						if (ad < X[aa].length) {
							aj[W] = X[aa][ad];
							W += 1
						}
					}
				}
				return aj
			};
			var A = function(Z, Y, V) {
				var T = n.getRSBlocks(Z, Y);
				var S = m();
				for ( var U = 0; U < V.length; U += 1) {
					var X = V[U];
					S.put(X.getMode(), 4);
					S.put(X.getLength(), o.getLengthInBits(X.getMode(), Z));
					X.write(S)
				}
				var W = 0;
				for ( var U = 0; U < T.length; U += 1) {
					W += T[U].dataCount
				}
				if (S.getLengthInBits() > W * 8) {
					throw new Error("code length overflow. ("
							+ S.getLengthInBits() + ">" + W * 8 + ")")
				}
				if (S.getLengthInBits() + 4 <= W * 8) {
					S.put(0, 4)
				}
				while (S.getLengthInBits() % 8 != 0) {
					S.putBit(false)
				}
				while (true) {
					if (S.getLengthInBits() >= W * 8) {
						break
					}
					S.put(R, 8);
					if (S.getLengthInBits() >= W * 8) {
						break
					}
					S.put(Q, 8)
				}
				return O(S, T)
			};
			N.addData = function(T) {
				var S = u(T);
				H.push(S);
				x = null
			};
			N.isDark = function(T, S) {
				if (T < 0 || M <= T || S < 0 || M <= S) {
					throw new Error(T + "," + S)
				}
				return B[T][S]
			};
			N.getModuleCount = function() {
				return M
			};
			N.make = function() {
				K(false, C())
			};
			N.createTableTag = function(W, U) {
				W = W || 2;
				U = (typeof U == "undefined") ? W * 4 : U;
				var S = "";
				S += '<table style="';
				S += " border-width: 0px; border-style: none;";
				S += " border-collapse: collapse;";
				S += " padding: 0px; margin: " + U + "px;";
				S += '">';
				S += "<tbody>";
				for ( var T = 0; T < N.getModuleCount(); T += 1) {
					S += "<tr>";
					for ( var V = 0; V < N.getModuleCount(); V += 1) {
						S += '<td style="';
						S += " border-width: 0px; border-style: none;";
						S += " border-collapse: collapse;";
						S += " padding: 0px; margin: 0px;";
						S += " width: " + W + "px;";
						S += " height: " + W + "px;";
						S += " background-color: ";
						S += N.isDark(T, V) ? "#000000" : "#ffffff";
						S += ";";
						S += '"/>'
					}
					S += "</tr>"
				}
				S += "</tbody>";
				S += "</table>";
				return S
			};
			N.createSvgTag = function(T, V) {
				T = T || 2;
				V = (typeof V == "undefined") ? T * 4 : V;
				var aa = N.getModuleCount() * T + V * 2;
				var W, Y, S, U, Z = "", X;
				X = "l" + T + ",0 0," + T + " -" + T + ",0 0,-" + T + "z ";
				Z += "<svg";
				Z += ' width="' + aa + 'px"';
				Z += ' height="' + aa + 'px"';
				Z += ' xmlns="http://www.w3.org/2000/svg"';
				Z += ">";
				Z += '<path d="';
				for (S = 0; S < N.getModuleCount(); S += 1) {
					U = S * T + V;
					for (W = 0; W < N.getModuleCount(); W += 1) {
						if (N.isDark(S, W)) {
							Y = W * T + V;
							Z += "M" + Y + "," + U + X
						}
					}
				}
				Z += '" stroke="transparent" fill="black"/>';
				Z += "</svg>";
				return Z
			};
			N.createImgTag = function(W, V) {
				W = W || 2;
				V = (typeof V == "undefined") ? W * 4 : V;
				var U = N.getModuleCount() * W + V * 2;
				var T = V;
				var S = U - V;
				return k(U, U, function(X, aa) {
					if (T <= X && X < S && T <= aa && aa < S) {
						var Z = Math.floor((X - T) / W);
						var Y = Math.floor((aa - T) / W);
						return N.isDark(Y, Z) ? 0 : 1
					} else {
						return 1
					}
				})
			};
			N.createImgBase64 = function(W, V) {
				W = W || 2;
				V = (typeof V == "undefined") ? W * 4 : V;
				var U = N.getModuleCount() * W + V * 2;
				var T = V;
				var S = U - V;
				return r(U, U, function(X, aa) {
					if (T <= X && X < S && T <= aa && aa < S) {
						var Z = Math.floor((X - T) / W);
						var Y = Math.floor((aa - T) / W);
						return N.isDark(Y, Z) ? 0 : 1
					} else {
						return 1
					}
				})
			};
			return N
		};
		v.stringToBytes = function(z) {
			var x = new Array();
			for ( var y = 0; y < z.length; y += 1) {
				var A = z.charCodeAt(y);
				x.push(A & 255)
			}
			return x
		};
		v.createStringToBytes = function(A, z) {
			var y = function() {
				var K = h(A);
				var B = function() {
					var L = K.read();
					if (L == -1) {
						throw new Error()
					}
					return L
				};
				var D = 0;
				var E = {};
				while (true) {
					var I = K.read();
					if (I == -1) {
						break
					}
					var H = B();
					var G = B();
					var F = B();
					var C = String.fromCharCode((I << 8) | H);
					var J = (G << 8) | F;
					E[C] = J;
					D += 1
				}
				if (D != z) {
					throw new Error(D + " != " + z)
				}
				return E
			}();
			var x = "?".charCodeAt(0);
			return function(E) {
				var C = new Array();
				for ( var D = 0; D < E.length; D += 1) {
					var F = E.charCodeAt(D);
					if (F < 128) {
						C.push(F)
					} else {
						var B = y[E.charAt(D)];
						if (typeof B == "number") {
							if ((B & 255) == B) {
								C.push(B)
							} else {
								C.push(B >>> 8);
								C.push(B & 255)
							}
						} else {
							C.push(x)
						}
					}
				}
				return C
			}
		};
		var q = {
			MODE_NUMBER : 1 << 0,
			MODE_ALPHA_NUM : 1 << 1,
			MODE_8BIT_BYTE : 1 << 2,
			MODE_KANJI : 1 << 3
		};
		var p = {
			L : 1,
			M : 0,
			Q : 3,
			H : 2
		};
		var j = {
			PATTERN000 : 0,
			PATTERN001 : 1,
			PATTERN010 : 2,
			PATTERN011 : 3,
			PATTERN100 : 4,
			PATTERN101 : 5,
			PATTERN110 : 6,
			PATTERN111 : 7
		};
		var o = function() {
			var A = [ [], [ 6, 18 ], [ 6, 22 ], [ 6, 26 ], [ 6, 30 ],
					[ 6, 34 ], [ 6, 22, 38 ], [ 6, 24, 42 ], [ 6, 26, 46 ],
					[ 6, 28, 50 ], [ 6, 30, 54 ], [ 6, 32, 58 ], [ 6, 34, 62 ],
					[ 6, 26, 46, 66 ], [ 6, 26, 48, 70 ], [ 6, 26, 50, 74 ],
					[ 6, 30, 54, 78 ], [ 6, 30, 56, 82 ], [ 6, 30, 58, 86 ],
					[ 6, 34, 62, 90 ], [ 6, 28, 50, 72, 94 ],
					[ 6, 26, 50, 74, 98 ], [ 6, 30, 54, 78, 102 ],
					[ 6, 28, 54, 80, 106 ], [ 6, 32, 58, 84, 110 ],
					[ 6, 30, 58, 86, 114 ], [ 6, 34, 62, 90, 118 ],
					[ 6, 26, 50, 74, 98, 122 ], [ 6, 30, 54, 78, 102, 126 ],
					[ 6, 26, 52, 78, 104, 130 ], [ 6, 30, 56, 82, 108, 134 ],
					[ 6, 34, 60, 86, 112, 138 ], [ 6, 30, 58, 86, 114, 142 ],
					[ 6, 34, 62, 90, 118, 146 ],
					[ 6, 30, 54, 78, 102, 126, 150 ],
					[ 6, 24, 50, 76, 102, 128, 154 ],
					[ 6, 28, 54, 80, 106, 132, 158 ],
					[ 6, 32, 58, 84, 110, 136, 162 ],
					[ 6, 26, 54, 82, 110, 138, 166 ],
					[ 6, 30, 58, 86, 114, 142, 170 ] ];
			var x = (1 << 10) | (1 << 8) | (1 << 5) | (1 << 4) | (1 << 2)
					| (1 << 1) | (1 << 0);
			var C = (1 << 12) | (1 << 11) | (1 << 10) | (1 << 9) | (1 << 8)
					| (1 << 5) | (1 << 2) | (1 << 0);
			var z = (1 << 14) | (1 << 12) | (1 << 10) | (1 << 4) | (1 << 1);
			var B = {};
			var y = function(D) {
				var E = 0;
				while (D != 0) {
					E += 1;
					D >>>= 1
				}
				return E
			};
			B.getBCHTypeInfo = function(D) {
				var E = D << 10;
				while (y(E) - y(x) >= 0) {
					E ^= (x << (y(E) - y(x)))
				}
				return ((D << 10) | E) ^ z
			};
			B.getBCHTypeNumber = function(D) {
				var E = D << 12;
				while (y(E) - y(C) >= 0) {
					E ^= (C << (y(E) - y(C)))
				}
				return (D << 12) | E
			};
			B.getPatternPosition = function(D) {
				return A[D - 1]
			};
			B.getMaskFunction = function(D) {
				switch (D) {
				case j.PATTERN000:
					return function(F, E) {
						return (F + E) % 2 == 0
					};
				case j.PATTERN001:
					return function(F, E) {
						return F % 2 == 0
					};
				case j.PATTERN010:
					return function(F, E) {
						return E % 3 == 0
					};
				case j.PATTERN011:
					return function(F, E) {
						return (F + E) % 3 == 0
					};
				case j.PATTERN100:
					return function(F, E) {
						return (Math.floor(F / 2) + Math.floor(E / 3)) % 2 == 0
					};
				case j.PATTERN101:
					return function(F, E) {
						return (F * E) % 2 + (F * E) % 3 == 0
					};
				case j.PATTERN110:
					return function(F, E) {
						return ((F * E) % 2 + (F * E) % 3) % 2 == 0
					};
				case j.PATTERN111:
					return function(F, E) {
						return ((F * E) % 3 + (F + E) % 2) % 2 == 0
					};
				default:
					throw new Error("bad maskPattern:" + D)
				}
			};
			B.getErrorCorrectPolynomial = function(E) {
				var D = l([ 1 ], 0);
				for ( var F = 0; F < E; F += 1) {
					D = D.multiply(l([ 1, w.gexp(F) ], 0))
				}
				return D
			};
			B.getLengthInBits = function(E, D) {
				if (1 <= D && D < 10) {
					switch (E) {
					case q.MODE_NUMBER:
						return 10;
					case q.MODE_ALPHA_NUM:
						return 9;
					case q.MODE_8BIT_BYTE:
						return 8;
					case q.MODE_KANJI:
						return 8;
					default:
						throw new Error("mode:" + E)
					}
				} else {
					if (D < 27) {
						switch (E) {
						case q.MODE_NUMBER:
							return 12;
						case q.MODE_ALPHA_NUM:
							return 11;
						case q.MODE_8BIT_BYTE:
							return 16;
						case q.MODE_KANJI:
							return 10;
						default:
							throw new Error("mode:" + E)
						}
					} else {
						if (D < 41) {
							switch (E) {
							case q.MODE_NUMBER:
								return 14;
							case q.MODE_ALPHA_NUM:
								return 13;
							case q.MODE_8BIT_BYTE:
								return 16;
							case q.MODE_KANJI:
								return 12;
							default:
								throw new Error("mode:" + E)
							}
						} else {
							throw new Error("type:" + D)
						}
					}
				}
			};
			B.getLostPoint = function(M) {
				var F = M.getModuleCount();
				var G = 0;
				for ( var O = 0; O < F; O += 1) {
					for ( var E = 0; E < F; E += 1) {
						var L = 0;
						var K = M.isDark(O, E);
						for ( var D = -1; D <= 1; D += 1) {
							if (O + D < 0 || F <= O + D) {
								continue
							}
							for ( var J = -1; J <= 1; J += 1) {
								if (E + J < 0 || F <= E + J) {
									continue
								}
								if (D == 0 && J == 0) {
									continue
								}
								if (K == M.isDark(O + D, E + J)) {
									L += 1
								}
							}
						}
						if (L > 5) {
							G += (3 + L - 5)
						}
					}
				}
				for ( var O = 0; O < F - 1; O += 1) {
					for ( var E = 0; E < F - 1; E += 1) {
						var H = 0;
						if (M.isDark(O, E)) {
							H += 1
						}
						if (M.isDark(O + 1, E)) {
							H += 1
						}
						if (M.isDark(O, E + 1)) {
							H += 1
						}
						if (M.isDark(O + 1, E + 1)) {
							H += 1
						}
						if (H == 0 || H == 4) {
							G += 3
						}
					}
				}
				for ( var O = 0; O < F; O += 1) {
					for ( var E = 0; E < F - 6; E += 1) {
						if (M.isDark(O, E) && !M.isDark(O, E + 1)
								&& M.isDark(O, E + 2) && M.isDark(O, E + 3)
								&& M.isDark(O, E + 4) && !M.isDark(O, E + 5)
								&& M.isDark(O, E + 6)) {
							G += 40
						}
					}
				}
				for ( var E = 0; E < F; E += 1) {
					for ( var O = 0; O < F - 6; O += 1) {
						if (M.isDark(O, E) && !M.isDark(O + 1, E)
								&& M.isDark(O + 2, E) && M.isDark(O + 3, E)
								&& M.isDark(O + 4, E) && !M.isDark(O + 5, E)
								&& M.isDark(O + 6, E)) {
							G += 40
						}
					}
				}
				var N = 0;
				for ( var E = 0; E < F; E += 1) {
					for ( var O = 0; O < F; O += 1) {
						if (M.isDark(O, E)) {
							N += 1
						}
					}
				}
				var I = Math.abs(100 * N / F / F - 50) / 5;
				G += I * 10;
				return G
			};
			return B
		}();
		var w = function() {
			var x = new Array(256);
			var z = new Array(256);
			for ( var y = 0; y < 8; y += 1) {
				x[y] = 1 << y
			}
			for ( var y = 8; y < 256; y += 1) {
				x[y] = x[y - 4] ^ x[y - 5] ^ x[y - 6] ^ x[y - 8]
			}
			for ( var y = 0; y < 255; y += 1) {
				z[x[y]] = y
			}
			var A = {};
			A.glog = function(B) {
				if (B < 1) {
					throw new Error("glog(" + B + ")")
				}
				return z[B]
			};
			A.gexp = function(B) {
				while (B < 0) {
					B += 255
				}
				while (B >= 256) {
					B -= 255
				}
				return x[B]
			};
			return A
		}();
		function l(y, x) {
			if (typeof y.length == "undefined") {
				throw new Error(y.length + "/" + x)
			}
			var z = function() {
				var D = 0;
				while (D < y.length && y[D] == 0) {
					D += 1
				}
				var C = new Array(y.length - D + x);
				for ( var B = 0; B < y.length - D; B += 1) {
					C[B] = y[B + D]
				}
				return C
			}();
			var A = {};
			A.getAt = function(B) {
				return z[B]
			};
			A.getLength = function() {
				return z.length
			};
			A.multiply = function(E) {
				var C = new Array(A.getLength() + E.getLength() - 1);
				for ( var D = 0; D < A.getLength(); D += 1) {
					for ( var B = 0; B < E.getLength(); B += 1) {
						C[D + B] ^= w.gexp(w.glog(A.getAt(D))
								+ w.glog(E.getAt(B)))
					}
				}
				return l(C, 0)
			};
			A.mod = function(E) {
				if (A.getLength() - E.getLength() < 0) {
					return A
				}
				var D = w.glog(A.getAt(0)) - w.glog(E.getAt(0));
				var B = new Array(A.getLength());
				for ( var C = 0; C < A.getLength(); C += 1) {
					B[C] = A.getAt(C)
				}
				for ( var C = 0; C < E.getLength(); C += 1) {
					B[C] ^= w.gexp(w.glog(E.getAt(C)) + D)
				}
				return l(B, 0).mod(E)
			};
			return A
		}
		var n = function() {
			var y = [ [ 1, 26, 19 ], [ 1, 26, 16 ], [ 1, 26, 13 ],
					[ 1, 26, 9 ], [ 1, 44, 34 ], [ 1, 44, 28 ], [ 1, 44, 22 ],
					[ 1, 44, 16 ], [ 1, 70, 55 ], [ 1, 70, 44 ], [ 2, 35, 17 ],
					[ 2, 35, 13 ], [ 1, 100, 80 ], [ 2, 50, 32 ],
					[ 2, 50, 24 ], [ 4, 25, 9 ], [ 1, 134, 108 ],
					[ 2, 67, 43 ], [ 2, 33, 15, 2, 34, 16 ],
					[ 2, 33, 11, 2, 34, 12 ], [ 2, 86, 68 ], [ 4, 43, 27 ],
					[ 4, 43, 19 ], [ 4, 43, 15 ], [ 2, 98, 78 ], [ 4, 49, 31 ],
					[ 2, 32, 14, 4, 33, 15 ], [ 4, 39, 13, 1, 40, 14 ],
					[ 2, 121, 97 ], [ 2, 60, 38, 2, 61, 39 ],
					[ 4, 40, 18, 2, 41, 19 ], [ 4, 40, 14, 2, 41, 15 ],
					[ 2, 146, 116 ], [ 3, 58, 36, 2, 59, 37 ],
					[ 4, 36, 16, 4, 37, 17 ], [ 4, 36, 12, 4, 37, 13 ],
					[ 2, 86, 68, 2, 87, 69 ], [ 4, 69, 43, 1, 70, 44 ],
					[ 6, 43, 19, 2, 44, 20 ], [ 6, 43, 15, 2, 44, 16 ],
					[ 4, 101, 81 ], [ 1, 80, 50, 4, 81, 51 ],
					[ 4, 50, 22, 4, 51, 23 ], [ 3, 36, 12, 8, 37, 13 ],
					[ 2, 116, 92, 2, 117, 93 ], [ 6, 58, 36, 2, 59, 37 ],
					[ 4, 46, 20, 6, 47, 21 ], [ 7, 42, 14, 4, 43, 15 ],
					[ 4, 133, 107 ], [ 8, 59, 37, 1, 60, 38 ],
					[ 8, 44, 20, 4, 45, 21 ], [ 12, 33, 11, 4, 34, 12 ],
					[ 3, 145, 115, 1, 146, 116 ], [ 4, 64, 40, 5, 65, 41 ],
					[ 11, 36, 16, 5, 37, 17 ], [ 11, 36, 12, 5, 37, 13 ],
					[ 5, 109, 87, 1, 110, 88 ], [ 5, 65, 41, 5, 66, 42 ],
					[ 5, 54, 24, 7, 55, 25 ], [ 11, 36, 12, 7, 37, 13 ],
					[ 5, 122, 98, 1, 123, 99 ], [ 7, 73, 45, 3, 74, 46 ],
					[ 15, 43, 19, 2, 44, 20 ], [ 3, 45, 15, 13, 46, 16 ],
					[ 1, 135, 107, 5, 136, 108 ], [ 10, 74, 46, 1, 75, 47 ],
					[ 1, 50, 22, 15, 51, 23 ], [ 2, 42, 14, 17, 43, 15 ],
					[ 5, 150, 120, 1, 151, 121 ], [ 9, 69, 43, 4, 70, 44 ],
					[ 17, 50, 22, 1, 51, 23 ], [ 2, 42, 14, 19, 43, 15 ],
					[ 3, 141, 113, 4, 142, 114 ], [ 3, 70, 44, 11, 71, 45 ],
					[ 17, 47, 21, 4, 48, 22 ], [ 9, 39, 13, 16, 40, 14 ],
					[ 3, 135, 107, 5, 136, 108 ], [ 3, 67, 41, 13, 68, 42 ],
					[ 15, 54, 24, 5, 55, 25 ], [ 15, 43, 15, 10, 44, 16 ],
					[ 4, 144, 116, 4, 145, 117 ], [ 17, 68, 42 ],
					[ 17, 50, 22, 6, 51, 23 ], [ 19, 46, 16, 6, 47, 17 ],
					[ 2, 139, 111, 7, 140, 112 ], [ 17, 74, 46 ],
					[ 7, 54, 24, 16, 55, 25 ], [ 34, 37, 13 ],
					[ 4, 151, 121, 5, 152, 122 ], [ 4, 75, 47, 14, 76, 48 ],
					[ 11, 54, 24, 14, 55, 25 ], [ 16, 45, 15, 14, 46, 16 ],
					[ 6, 147, 117, 4, 148, 118 ], [ 6, 73, 45, 14, 74, 46 ],
					[ 11, 54, 24, 16, 55, 25 ], [ 30, 46, 16, 2, 47, 17 ],
					[ 8, 132, 106, 4, 133, 107 ], [ 8, 75, 47, 13, 76, 48 ],
					[ 7, 54, 24, 22, 55, 25 ], [ 22, 45, 15, 13, 46, 16 ],
					[ 10, 142, 114, 2, 143, 115 ], [ 19, 74, 46, 4, 75, 47 ],
					[ 28, 50, 22, 6, 51, 23 ], [ 33, 46, 16, 4, 47, 17 ],
					[ 8, 152, 122, 4, 153, 123 ], [ 22, 73, 45, 3, 74, 46 ],
					[ 8, 53, 23, 26, 54, 24 ], [ 12, 45, 15, 28, 46, 16 ],
					[ 3, 147, 117, 10, 148, 118 ], [ 3, 73, 45, 23, 74, 46 ],
					[ 4, 54, 24, 31, 55, 25 ], [ 11, 45, 15, 31, 46, 16 ],
					[ 7, 146, 116, 7, 147, 117 ], [ 21, 73, 45, 7, 74, 46 ],
					[ 1, 53, 23, 37, 54, 24 ], [ 19, 45, 15, 26, 46, 16 ],
					[ 5, 145, 115, 10, 146, 116 ], [ 19, 75, 47, 10, 76, 48 ],
					[ 15, 54, 24, 25, 55, 25 ], [ 23, 45, 15, 25, 46, 16 ],
					[ 13, 145, 115, 3, 146, 116 ], [ 2, 74, 46, 29, 75, 47 ],
					[ 42, 54, 24, 1, 55, 25 ], [ 23, 45, 15, 28, 46, 16 ],
					[ 17, 145, 115 ], [ 10, 74, 46, 23, 75, 47 ],
					[ 10, 54, 24, 35, 55, 25 ], [ 19, 45, 15, 35, 46, 16 ],
					[ 17, 145, 115, 1, 146, 116 ], [ 14, 74, 46, 21, 75, 47 ],
					[ 29, 54, 24, 19, 55, 25 ], [ 11, 45, 15, 46, 46, 16 ],
					[ 13, 145, 115, 6, 146, 116 ], [ 14, 74, 46, 23, 75, 47 ],
					[ 44, 54, 24, 7, 55, 25 ], [ 59, 46, 16, 1, 47, 17 ],
					[ 12, 151, 121, 7, 152, 122 ], [ 12, 75, 47, 26, 76, 48 ],
					[ 39, 54, 24, 14, 55, 25 ], [ 22, 45, 15, 41, 46, 16 ],
					[ 6, 151, 121, 14, 152, 122 ], [ 6, 75, 47, 34, 76, 48 ],
					[ 46, 54, 24, 10, 55, 25 ], [ 2, 45, 15, 64, 46, 16 ],
					[ 17, 152, 122, 4, 153, 123 ], [ 29, 74, 46, 14, 75, 47 ],
					[ 49, 54, 24, 10, 55, 25 ], [ 24, 45, 15, 46, 46, 16 ],
					[ 4, 152, 122, 18, 153, 123 ], [ 13, 74, 46, 32, 75, 47 ],
					[ 48, 54, 24, 14, 55, 25 ], [ 42, 45, 15, 32, 46, 16 ],
					[ 20, 147, 117, 4, 148, 118 ], [ 40, 75, 47, 7, 76, 48 ],
					[ 43, 54, 24, 22, 55, 25 ], [ 10, 45, 15, 67, 46, 16 ],
					[ 19, 148, 118, 6, 149, 119 ], [ 18, 75, 47, 31, 76, 48 ],
					[ 34, 54, 24, 34, 55, 25 ], [ 20, 45, 15, 61, 46, 16 ] ];
			var x = function(B, C) {
				var D = {};
				D.totalCount = B;
				D.dataCount = C;
				return D
			};
			var A = {};
			var z = function(C, B) {
				switch (B) {
				case p.L:
					return y[(C - 1) * 4 + 0];
				case p.M:
					return y[(C - 1) * 4 + 1];
				case p.Q:
					return y[(C - 1) * 4 + 2];
				case p.H:
					return y[(C - 1) * 4 + 3];
				default:
					return undefined
				}
			};
			A.getRSBlocks = function(D, J) {
				var C = z(D, J);
				if (typeof C == "undefined") {
					throw new Error("bad rs block @ typeNumber:" + D
							+ "/errorCorrectLevel:" + J)
				}
				var B = C.length / 3;
				var H = new Array();
				for ( var F = 0; F < B; F += 1) {
					var G = C[F * 3 + 0];
					var K = C[F * 3 + 1];
					var I = C[F * 3 + 2];
					for ( var E = 0; E < G; E += 1) {
						H.push(x(K, I))
					}
				}
				return H
			};
			return A
		}();
		var m = function() {
			var x = new Array();
			var z = 0;
			var y = {};
			y.getBuffer = function() {
				return x
			};
			y.getAt = function(A) {
				var B = Math.floor(A / 8);
				return ((x[B] >>> (7 - A % 8)) & 1) == 1
			};
			y.put = function(A, C) {
				for ( var B = 0; B < C; B += 1) {
					y.putBit(((A >>> (C - B - 1)) & 1) == 1)
				}
			};
			y.getLengthInBits = function() {
				return z
			};
			y.putBit = function(B) {
				var A = Math.floor(z / 8);
				if (x.length <= A) {
					x.push(0)
				}
				if (B) {
					x[A] |= (128 >>> (z % 8))
				}
				z += 1
			};
			return y
		};
		var u = function(A) {
			var z = q.MODE_8BIT_BYTE;
			var x = A;
			var y = v.stringToBytes(A);
			var B = {};
			B.getMode = function() {
				return z
			};
			B.getLength = function(C) {
				return y.length
			};
			B.write = function(C) {
				for ( var D = 0; D < y.length; D += 1) {
					C.put(y[D], 8)
				}
			};
			return B
		};
		var s = function() {
			var x = new Array();
			var y = {};
			y.writeByte = function(z) {
				x.push(z & 255)
			};
			y.writeShort = function(z) {
				y.writeByte(z);
				y.writeByte(z >>> 8)
			};
			y.writeBytes = function(A, C, z) {
				C = C || 0;
				z = z || A.length;
				for ( var B = 0; B < z; B += 1) {
					y.writeByte(A[B + C])
				}
			};
			y.writeString = function(A) {
				for ( var z = 0; z < A.length; z += 1) {
					y.writeByte(A.charCodeAt(z))
				}
			};
			y.toByteArray = function() {
				return x
			};
			y.toString = function() {
				var A = "";
				A += "[";
				for ( var z = 0; z < x.length; z += 1) {
					if (z > 0) {
						A += ","
					}
					A += x[z]
				}
				A += "]";
				return A
			};
			return y
		};
		var i = function() {
			var y = 0;
			var B = 0;
			var D = 0;
			var x = "";
			var C = {};
			var A = function(E) {
				x += String.fromCharCode(z(E & 63))
			};
			var z = function(E) {
				if (E < 0) {
				} else {
					if (E < 26) {
						return 65 + E
					} else {
						if (E < 52) {
							return 97 + (E - 26)
						} else {
							if (E < 62) {
								return 48 + (E - 52)
							} else {
								if (E == 62) {
									return 43
								} else {
									if (E == 63) {
										return 47
									}
								}
							}
						}
					}
				}
				throw new Error("n:" + E)
			};
			C.writeByte = function(E) {
				y = (y << 8) | (E & 255);
				B += 8;
				D += 1;
				while (B >= 6) {
					A(y >>> (B - 6));
					B -= 6
				}
			};
			C.flush = function() {
				if (B > 0) {
					A(y << (6 - B));
					y = 0;
					B = 0
				}
				if (D % 3 != 0) {
					var F = 3 - D % 3;
					for ( var E = 0; E < F; E += 1) {
						x += "="
					}
				}
			};
			C.toString = function() {
				return x
			};
			return C
		};
		var h = function(B) {
			var D = B;
			var x = 0;
			var y = 0;
			var z = 0;
			var C = {};
			C.read = function() {
				while (z < 8) {
					if (x >= D.length) {
						if (z == 0) {
							return -1
						}
						throw new Error("unexpected end of file./" + z)
					}
					var F = D.charAt(x);
					x += 1;
					if (F == "=") {
						z = 0;
						return -1
					} else {
						if (F.match(/^\s$/)) {
							continue
						}
					}
					y = (y << 6) | A(F.charCodeAt(0));
					z += 6
				}
				var E = (y >>> (z - 8)) & 255;
				z -= 8;
				return E
			};
			var A = function(E) {
				if (65 <= E && E <= 90) {
					return E - 65
				} else {
					if (97 <= E && E <= 122) {
						return E - 97 + 26
					} else {
						if (48 <= E && E <= 57) {
							return E - 48 + 52
						} else {
							if (E == 43) {
								return 62
							} else {
								if (E == 47) {
									return 63
								} else {
									throw new Error("c:" + E)
								}
							}
						}
					}
				}
			};
			return C
		};
		var t = function(x, E) {
			var A = x;
			var B = E;
			var y = new Array(x * E);
			var C = {};
			C.setPixel = function(G, I, H) {
				y[I * A + G] = H
			};
			C.write = function(H) {
				H.writeString("GIF87a");
				H.writeShort(A);
				H.writeShort(B);
				H.writeByte(128);
				H.writeByte(0);
				H.writeByte(0);
				H.writeByte(0);
				H.writeByte(0);
				H.writeByte(0);
				H.writeByte(255);
				H.writeByte(255);
				H.writeByte(255);
				H.writeString(",");
				H.writeShort(0);
				H.writeShort(0);
				H.writeShort(A);
				H.writeShort(B);
				H.writeByte(0);
				var I = 2;
				var G = z(I);
				H.writeByte(I);
				var J = 0;
				while (G.length - J > 255) {
					H.writeByte(255);
					H.writeBytes(G, J, 255);
					J += 255
				}
				H.writeByte(G.length - J);
				H.writeBytes(G, J, G.length - J);
				H.writeByte(0);
				H.writeString(";")
			};
			var D = function(H) {
				var I = H;
				var J = 0;
				var G = 0;
				var K = {};
				K.write = function(M, L) {
					if ((M >>> L) != 0) {
						throw new Error("length over")
					}
					while (J + L >= 8) {
						I.writeByte(255 & ((M << J) | G));
						L -= (8 - J);
						M >>>= (8 - J);
						G = 0;
						J = 0
					}
					G = (M << J) | G;
					J = J + L
				};
				K.flush = function() {
					if (J > 0) {
						I.writeByte(G)
					}
				};
				return K
			};
			var z = function(H) {
				var L = 1 << H;
				var J = (1 << H) + 1;
				var N = H + 1;
				var P = F();
				for ( var K = 0; K < L; K += 1) {
					P.add(String.fromCharCode(K))
				}
				P.add(String.fromCharCode(L));
				P.add(String.fromCharCode(J));
				var I = s();
				var G = D(I);
				G.write(L, N);
				var O = 0;
				var Q = String.fromCharCode(y[O]);
				O += 1;
				while (O < y.length) {
					var M = String.fromCharCode(y[O]);
					O += 1;
					if (P.contains(Q + M)) {
						Q = Q + M
					} else {
						G.write(P.indexOf(Q), N);
						if (P.size() < 4095) {
							if (P.size() == (1 << N)) {
								N += 1
							}
							P.add(Q + M)
						}
						Q = M
					}
				}
				G.write(P.indexOf(Q), N);
				G.write(J, N);
				G.flush();
				return I.toByteArray()
			};
			var F = function() {
				var H = {};
				var G = 0;
				var I = {};
				I.add = function(J) {
					if (I.contains(J)) {
						throw new Error("dup key:" + J)
					}
					H[J] = G;
					G += 1
				};
				I.size = function() {
					return G
				};
				I.indexOf = function(J) {
					return H[J]
				};
				I.contains = function(J) {
					return typeof H[J] != "undefined"
				};
				return I
			};
			return C
		};
		var k = function(z, J, F, D) {
			var C = t(z, J);
			for ( var G = 0; G < J; G += 1) {
				for ( var I = 0; I < z; I += 1) {
					C.setPixel(I, G, F(I, G))
				}
			}
			var H = s();
			C.write(H);
			var E = i();
			var K = H.toByteArray();
			for ( var B = 0; B < K.length; B += 1) {
				E.writeByte(K[B])
			}
			E.flush();
			var A = "";
			A += "<img";
			A += '\u0020src="';
			A += "data:image/gif;base64,";
			A += E;
			A += '"';
			A += '\u0020width="';
			A += z;
			A += '"';
			A += '\u0020height="';
			A += J;
			A += '"';
			if (D) {
				A += '\u0020alt="';
				A += D;
				A += '"'
			}
			A += "/>";
			return A
		};
		var r = function(z, J, F, D) {
			var C = t(z, J);
			for ( var G = 0; G < J; G += 1) {
				for ( var I = 0; I < z; I += 1) {
					C.setPixel(I, G, F(I, G))
				}
			}
			var H = s();
			C.write(H);
			var E = i();
			var K = H.toByteArray();
			for ( var B = 0; B < K.length; B += 1) {
				E.writeByte(K[B])
			}
			E.flush();
			var A = "";
			A += "data:image/gif;base64,";
			A += E;
			return A
		};
		return v
	}();
	(function(h) {
		if (typeof define === "function" && define.amd) {
			define([], h)
		} else {
			if (typeof exports === "object") {
				module.exports = h()
			}
		}
	}(function() {
		return d
	}));
	function b(j) {
		var i = function(o, n, m, l) {
			var k = d(n || 4, m || "M");
			k.addData(o);
			k.make();
			return k.createImgBase64(8)
		};
		var h = d(4, "M");
		imgData = i(window.location.href, 8, "M");
		f.set(j, imgData)
	}
};