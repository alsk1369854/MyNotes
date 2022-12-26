const { add, getUser } = require('../index');

// toBe
test('toBe', () => {
    expect(add(2, 3)).toBe(5);
});

// not toBe
test('not toBe', () => {
    expect(add(2, 3)).not.toBe(7);
});

// toBeNull
test('toBeNull', () => {
    const number = null;
    expect(number).toBeNull();
});

// toBeGreaterThan
// toBeGreaterThanOrEqual
// toBeLessThan
// toBeLessThanOrEqual
test('toBe GreaterThan or LessThan', () => {
    expect(add(6, 4)).toBeGreaterThan(3);
    expect(add(2, 3)).toBeLessThanOrEqual(7);
});

// toBeTruthy 
// toBeFalsy
test('toBe Truthy or Falsy', () => {
    const username = 'admin';
    expect(username === 'admin').toBeTruthy();
    expect(username === 'admins').toBeFalsy();
});

// toEqual
test('toEqual', () => {
    const user = { user: 'ming' };
    // expect(getUser()).toBe(user); // Error object can not use .toBe() compare
    expect(getUser()).toEqual(user);
});

// toContain
test('toContain', () => {
    const usernames = ["John", "Admin", "Smith"];
    expect(usernames).toContain("Admin")
});

// toMatch
test('toMatch', () => {
    expect("Ming").toMatch(/m/i);
});




